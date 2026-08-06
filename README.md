# spring-security-extension

![Java](https://img.shields.io/badge/Java-8-orange) ![License](https://img.shields.io/badge/License-Apache%202.0-blue)

[1. Project Overview](#1-project-overview) | [2. Features & Status](#2-features--status) | [3. Requirements & Compatibility](#3-requirements--compatibility) | [4. Architecture & Modules](#4-architecture--modules) | [5. Installation](#5-installation) | [6. Quick Start](#6-quick-start) | [7. Configuration](#7-configuration) | [8. Core Usage / API](#8-core-usage--api) | [9. Testing & Build](#9-testing--build) | [10. Versioning & Branches](#10-versioning--branches) | [11. Contributing & License](#11-contributing--license)

## 1. Project Overview

`spring-security-extension` is a set of Spring Security extensions that is independent of Spring Boot and fully compatible with JDK 1.8. It provides servlet and reactive (WebFlux) building blocks on top of Spring Security: POST-based JSON authentication filters and providers, captcha resolvers, concurrent session control with Redis-backed session registry, structured auth response codes with i18n messages, security header helpers (CORS, CSRF, HSTS, Content-Security-Policy), JWT web filters, and security/web utilities.

It is a library layer for applications that configure Spring Security programmatically — it is not a Spring Boot starter with auto-configuration (property POJOs are provided for the application to wire).

Typical scenarios:

| Scenario | What this module contributes |
|:---|:---|
| POST-based JSON login | `PostRequestAuthenticationProcessingFilter`, `PostOnlyAuthenticationProcessingFilter`, `PostRequestAuthenticationProvider` |
| Captcha-protected login | `CaptchaResolver` + `SessionCaptchaResolver`, `NullCaptchaResolver` |
| Session control | `MySessionRegistryImpl`, `MyConcurrentSessionControlAuthenticationStrategy` |
| Reactive security (WebFlux) | `JwtAuthenticationWebFilter`, `MatchedServer*` / `Reactive*` handlers and entry points |
| Security headers | CORS/CSRF/HSTS/CSP support (`CustomCorsFilter`, `HttpParamsFilter`, header properties) |
| Structured errors & i18n | Auth exception/response models, `SpringSecurityBizMessageSource` |
| Utilities | `SubjectUtils`, `SecurityResponseUtils`, `WebSecurityUtils`, reactive variants |

## 2. Features & Status

Project status: pre-release development line (`1.0.x.*` snapshots); public API is still stabilizing until the first tagged release.

| Capability | Status | Notes |
|:---|:---|:---|
| POST-based JSON authentication | Stable | `PostRequestAuthenticationProcessingFilter` (built on `ObjectMapper`, optional `AntPathRequestMatcher`), `PostRequestAuthenticationProvider` (user-details adapter + `PasswordEncoder`) |
| Authentication handlers | Stable | Success/failure handlers, `ListenedAuthenticationSuccessHandler`, `ListenedAuthenticationFailureHandler`, `PostRequestAuthenticationEntryPoint`, `NeteaseUrlAuthenticationSuccessHandler` |
| Captcha support | Stable | `CaptchaResolver` interface with session-based and null implementations |
| Session management | Stable | `MySessionRegistryImpl`, `MyConcurrentSessionControlAuthenticationStrategy`, `SessionAuthenticationFailureHandler`, Redis-backed session registry |
| Exception handling | Stable | Structured auth response codes and i18n error messages (`AuthResponse`, `AuthResponseCode`, captcha exceptions, `SpringSecurityBizMessageSource`) |
| Reactive (WebFlux) | Stable | Entry points, success/failure handlers, `JwtAuthenticationWebFilter` for reactive applications |
| Security headers | Stable | CORS (`CustomCorsFilter`), HTTP params filter, header property models |
| Utilities | Stable | `SubjectUtils`, `SecurityResponseUtils`, `WebSecurityUtils`, `RemoteAddrUtils`, `ReactiveSubjectUtils`, `ReactiveSecurityResponseUtils`, `WebfluxUtils`, expression root/handler helpers |

## 3. Requirements & Compatibility

| Requirement | Version |
|:---|:---|
| JDK | 8+ |
| Maven | 3.6+ |
| Spring Security | 5.7.x (managed by `spring-security-bom`: core, config, web, crypto, messaging, aspects) |
| Spring Framework | 5.3.x (managed by `spring-framework-bom`) |
| easy4j sibling module | `jwt-issuer-api` (same `1.0.x.*` line) |
| Other runtime deps | commons-collections4, commons-io, commons-lang3, swagger-annotations, `spring-boot-starter-data-redis` (session registry), javax.servlet-api |

Version lines:

| Branch | JDK | Version pattern | Notes |
|:---|:---|:---|:---|
| `feature/1.0.x` | 8 | `1.0.x.*` | Current line; Spring Security 5.x / Spring Boot 2.x era |
| `feature/2.0.x` | 17 | `2.0.x.*` | Next line |
| `feature/3.0.x` | 21 | `3.0.x.*` | Future line |

## 4. Architecture & Modules

```
HTTP request (login / protected resource)
        |
        v
+------------------------------------+
| Servlet: PostRequestAuthenticating |
| Filter + Provider + EntryPoint     |
| (captcha, session control)         |
+------------------------------------+
        |
        v
+------------------------------------+
| Reactive: JwtAuthenticationWebFil- |
| ter + MatchedServer* / Reactive*   |
+------------------------------------+
        |
        v
Spring Security core (AuthenticationManager, session registry)
        |
        v
Auth result: success/failure handlers, structured AuthResponse
```

The project is a single jar module. Package layout under `org.springframework.security.boot`:

| Package | Responsibility |
|:---|:---|
| `biz/authentication` | Authentication filters, providers, handlers; `captcha` (captcha support); `nested` (matched entry points and handlers, servlet); `server` (reactive/WebFlux handlers) |
| `biz/exception` | Auth exceptions and response models |
| `biz/filter` | CORS and HTTP params filters |
| `biz/property` | Configuration property POJOs (incl. `header/` security header properties) |
| `biz/session` | Session management (registry, concurrent session strategy) |
| `biz/userdetails` | User details and JWT support |
| `utils` | Security and web utilities (servlet + reactive) |

## 5. Installation

Artifacts are published to the easy4j private repository and GitHub Releases; the project is not yet on Maven Central.

Maven:

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>spring-security-extension</artifactId>
    <version>1.0.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle:

```groovy
implementation 'io.github.easy4j:spring-security-extension:1.0.x.20260630-SNAPSHOT'
```

## 6. Quick Start

Wire a POST-based JSON login into a `WebSecurityConfigurerAdapter` (like the module's test configuration):

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.boot.biz.authentication.PostRequestAuthenticationProcessingFilter;
import org.springframework.security.boot.biz.authentication.PostRequestAuthenticationProvider;
import org.springframework.security.boot.biz.authentication.PostRequestAuthenticationSuccessHandler;
import org.springframework.security.boot.biz.authentication.PostRequestAuthenticationFailureHandler;

// inside the SecurityConfig (extends WebSecurityConfigurerAdapter):

PostRequestAuthenticationProvider authProvider =
        new PostRequestAuthenticationProvider(userDetailsServiceAdapter, passwordEncoder);

PostRequestAuthenticationProcessingFilter loginFilter =
        new PostRequestAuthenticationProcessingFilter(new ObjectMapper());
loginFilter.setAuthenticationManager(authenticationManager);
loginFilter.setAuthenticationSuccessHandler(new PostRequestAuthenticationSuccessHandler());
loginFilter.setAuthenticationFailureHandler(new PostRequestAuthenticationFailureHandler());
```

Expected result: `POST` requests carrying the JSON credentials are intercepted by the filter, authenticated through the provider, and routed to the configured success/failure handlers.

## 7. Configuration

Property POJOs are provided for programmatic wiring (no Spring Boot auto-binding):

| Property class | Purpose |
|:---|:---|
| `SecurityCaptchaProperties` | Captcha settings |
| `SecuritySessionMgtProperties` | Session management settings |
| `SecurityEntryPointProperties` | Authentication entry point settings |
| `SessionFixationPolicy` | Session fixation policy model |
| `property/header/*` | Security header settings (HSTS, frame options, content-type options, XSS protection, cache control, HPKP) |

The module also provides `JsonInvalidSessionStrategy`, `IgnoreLogoutHandler`, `TrustedRedirectStrategy` and `CustomWebSecurityExpressionHandler` / `CustomWebSecurityExpressionRoot` for the security configuration.

## 8. Core Usage / API

Reactive JWT filter for WebFlux applications:

```java
import org.springframework.security.boot.biz.authentication.server.JwtAuthenticationWebFilter;

JwtAuthenticationWebFilter jwtWebFilter = new JwtAuthenticationWebFilter();
// configure the JWT payload repository and failure/entry-point handlers,
// then register the filter in the reactive SecurityWebFilterChain
```

Session control:

```java
import org.springframework.security.boot.biz.session.MySessionRegistryImpl;
import org.springframework.security.boot.biz.session.MyConcurrentSessionControlAuthenticationStrategy;

MySessionRegistryImpl sessionRegistry = new MySessionRegistryImpl();
MyConcurrentSessionControlAuthenticationStrategy strategy =
        new MyConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
```

## 9. Testing & Build

Build and run tests:

```bash
mvn clean verify
```

- Test resources contain a `SecurityConfiguration` (`@EnableWebSecurity`, method-level security) used as the wiring template; no executable unit tests are shipped for this module yet, coverage is tracked via the JaCoCo report.
- The build is configured with the JaCoCo Maven plugin: a coverage report is generated at `target/site/jacoco/index.html` and a rule checks the bundle line coverage against a 90% minimum (`haltOnFailure=false`, so the check reports but does not fail the build).
- The `central` Maven profile (`mvn -Pcentral deploy`) attaches GPG signatures, sources and Javadoc jars for publishing.

## 10. Versioning & Branches

Three parallel version lines are maintained:

| Branch | JDK | Version pattern |
|:---|:---|:---|
| `feature/1.0.x` | 8 | `1.0.x.*` |
| `feature/2.0.x` | 17 | `2.0.x.*` |
| `feature/3.0.x` | 21 | `3.0.x.*` |

Maintenance policy: the `1.0.x` line is the actively developed line (current snapshot `1.0.x.20260630-SNAPSHOT`); `2.0.x` and `3.0.x` are forward porting lines targeting newer JDKs. Snapshots are built on demand; tagged releases are distributed via GitHub Releases.

## 11. Contributing & License

- Fork the repository and open a pull request; keep the `1.0.x` line compatible with JDK 8.
- Bug reports and feature requests are tracked via GitHub Issues.
- Licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
