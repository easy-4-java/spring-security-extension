# spring-security-extension

[English](./README.md) | [简体中文](./README.zh-CN.md)

[![Java](https://img.shields.io/badge/Java-8-orange)](https://github.com/easy-4-java/spring-security-extension) [![License](https://img.shields.io/badge/license-Apache%202.0-green)](./LICENSE)

spring-security-extension 是一组独立于 Spring Boot、完全兼容 JDK 1.8 的 Spring Security 扩展。

## 目录

- [1. Project Overview](#1-project-overview)
- [2. Features & Status](#2-features--status)
- [3. Requirements & Compatibility](#3-requirements--compatibility)
- [4. Architecture & Modules](#4-architecture--modules)
- [5. Installation](#5-installation)
- [6. Quick Start](#6-quick-start)
- [7. Configuration](#7-configuration)
- [8. Core Usage / API](#8-core-usage--api)
- [9. Testing & Build](#9-testing--build)
- [10. Versioning & Branches](#10-versioning--branches)
- [11. Contributing & License](#11-contributing--license)

## 1. Project Overview

`spring-security-extension` 是一组独立于 Spring Boot、完全兼容 JDK 1.8 的 Spring Security 扩展。它在 Spring Security 之上提供 Servlet 与响应式（WebFlux）积木：基于 POST 的 JSON 认证过滤器与 Provider、验证码解析器、基于 Redis 会话注册表的并发会话控制、结构化的认证响应码与 i18n 消息、安全头辅助（CORS、CSRF、HSTS、Content-Security-Policy）、JWT Web 过滤器以及安全/Web 工具类。

它是面向以编程方式配置 Spring Security 的应用的库层——不是带自动配置的 Spring Boot Starter（属性 POJO 交由应用自行装配）。

典型场景：

| 场景 | 本模块提供的组件 |
|:---|:---|
| 基于 POST 的 JSON 登录 | `PostRequestAuthenticationProcessingFilter`、`PostOnlyAuthenticationProcessingFilter`、`PostRequestAuthenticationProvider` |
| 验证码保护的登录 | `CaptchaResolver` + `SessionCaptchaResolver`、`NullCaptchaResolver` |
| 会话控制 | `MySessionRegistryImpl`、`MyConcurrentSessionControlAuthenticationStrategy` |
| 响应式安全（WebFlux） | `JwtAuthenticationWebFilter`、`MatchedServer*` / `Reactive*` 处理器与入口点 |
| 安全头 | CORS/CSRF/HSTS/CSP 支持（`CustomCorsFilter`、`HttpParamsFilter`、header 属性） |
| 结构化错误与 i18n | 认证异常/响应模型、`SpringSecurityBizMessageSource` |
| 工具类 | `SubjectUtils`、`SecurityResponseUtils`、`WebSecurityUtils` 及响应式变体 |

## 2. Features & Status

项目状态：`1.0.x.*` 预发布开发线（快照版本）；在首个正式 Release 标签之前，公开 API 仍在稳定过程中。

| 能力 | 状态 | 说明 |
|:---|:---|:---|
| 基于 POST 的 JSON 认证 | 稳定 | `PostRequestAuthenticationProcessingFilter`（基于 `ObjectMapper`，可选 `AntPathRequestMatcher`）、`PostRequestAuthenticationProvider`（用户详情适配器 + `PasswordEncoder`） |
| 认证处理器 | 稳定 | 成功/失败处理器、`ListenedAuthenticationSuccessHandler`、`ListenedAuthenticationFailureHandler`、`PostRequestAuthenticationEntryPoint`、`NeteaseUrlAuthenticationSuccessHandler` |
| 验证码支持 | 稳定 | `CaptchaResolver` 接口，含基于会话与空实现 |
| 会话管理 | 稳定 | `MySessionRegistryImpl`、`MyConcurrentSessionControlAuthenticationStrategy`、`SessionAuthenticationFailureHandler`、基于 Redis 的会话注册表 |
| 异常处理 | 稳定 | 结构化的认证响应码与 i18n 错误消息（`AuthResponse`、`AuthResponseCode`、验证码异常、`SpringSecurityBizMessageSource`） |
| 响应式（WebFlux） | 稳定 | 入口点、成功/失败处理器、面向响应式应用的 `JwtAuthenticationWebFilter` |
| 安全头 | 稳定 | CORS（`CustomCorsFilter`）、HTTP 参数过滤器、header 属性模型 |
| 工具类 | 稳定 | `SubjectUtils`、`SecurityResponseUtils`、`WebSecurityUtils`、`RemoteAddrUtils`、`ReactiveSubjectUtils`、`ReactiveSecurityResponseUtils`、`WebfluxUtils`、表达式根/处理器辅助 |

## 3. Requirements & Compatibility

| 要求 | 版本 |
|:---|:---|
| JDK | 8+ |
| Maven | 3.6+ |
| Spring Security | 5.7.x（由 `spring-security-bom` 管理：core、config、web、crypto、messaging、aspects） |
| Spring Framework | 5.3.x（由 `spring-framework-bom` 管理） |
| easy4j 兄弟模块 | `jwt-issuer-api`（同一 `1.0.x.*` 版本线） |
| 其他运行依赖 | commons-collections4、commons-io、commons-lang3、swagger-annotations、`spring-boot-starter-data-redis`（会话注册表）、javax.servlet-api |

版本线：

| 分支 | JDK | 版本模式 | 说明 |
|:---|:---|:---|:---|
| `feature/1.0.x` | 8 | `1.0.x.*` | 当前开发线；Spring Security 5.x / Spring Boot 2.x 时代 |
| `feature/2.0.x` | 17 | `2.0.x.*` | 下一条版本线 |
| `feature/3.0.x` | 21 | `3.0.x.*` | 未来版本线 |

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

本工程为单 jar 模块，包结构位于 `org.springframework.security.boot`：

| 包 | 职责 |
|:---|:---|
| `biz/authentication` | 认证过滤器、Provider、处理器；`captcha`（验证码支持）；`nested`（匹配式入口点与处理器，Servlet）；`server`（响应式/WebFlux 处理器） |
| `biz/exception` | 认证异常与响应模型 |
| `biz/filter` | CORS 与 HTTP 参数过滤器 |
| `biz/property` | 配置属性 POJO（含 `header/` 安全头属性） |
| `biz/session` | 会话管理（注册表、并发会话策略） |
| `biz/userdetails` | 用户详情与 JWT 支持 |
| `utils` | 安全与 Web 工具（Servlet + 响应式） |

## 5. Installation

制品发布到 easy4j 私有仓库与 GitHub Releases，暂未发布 Maven Central。

Maven：

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>spring-security-extension</artifactId>
    <version>1.0.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle：

```groovy
implementation 'io.github.easy4j:spring-security-extension:1.0.x.20260630-SNAPSHOT'
```

## 6. Quick Start

在 `WebSecurityConfigurerAdapter` 中装配基于 POST 的 JSON 登录（与本模块测试配置一致）：

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

// 在 SecurityConfig（继承 WebSecurityConfigurerAdapter）内部：

PostRequestAuthenticationProvider authProvider =
        new PostRequestAuthenticationProvider(userDetailsServiceAdapter, passwordEncoder);

PostRequestAuthenticationProcessingFilter loginFilter =
        new PostRequestAuthenticationProcessingFilter(new ObjectMapper());
loginFilter.setAuthenticationManager(authenticationManager);
loginFilter.setAuthenticationSuccessHandler(new PostRequestAuthenticationSuccessHandler());
loginFilter.setAuthenticationFailureHandler(new PostRequestAuthenticationFailureHandler());
```

预期结果：携带 JSON 凭据的 `POST` 请求被过滤器拦截，经 Provider 完成认证，并路由到配置的成功/失败处理器。

## 7. Configuration

提供属性 POJO 供编程式装配（无 Spring Boot 自动绑定）：

| 属性类 | 用途 |
|:---|:---|
| `SecurityCaptchaProperties` | 验证码设置 |
| `SecuritySessionMgtProperties` | 会话管理设置 |
| `SecurityEntryPointProperties` | 认证入口点设置 |
| `SessionFixationPolicy` | 会话固定策略模型 |
| `property/header/*` | 安全头设置（HSTS、frame options、content-type options、XSS protection、cache control、HPKP） |

模块还提供 `JsonInvalidSessionStrategy`、`IgnoreLogoutHandler`、`TrustedRedirectStrategy` 与 `CustomWebSecurityExpressionHandler` / `CustomWebSecurityExpressionRoot` 用于安全配置。

## 8. Core Usage / API

面向 WebFlux 应用的响应式 JWT 过滤器：

```java
import org.springframework.security.boot.biz.authentication.server.JwtAuthenticationWebFilter;

JwtAuthenticationWebFilter jwtWebFilter = new JwtAuthenticationWebFilter();
// 配置 JWT payload repository 与失败/入口点处理器，
// 然后将过滤器注册到响应式 SecurityWebFilterChain
```

会话控制：

```java
import org.springframework.security.boot.biz.session.MySessionRegistryImpl;
import org.springframework.security.boot.biz.session.MyConcurrentSessionControlAuthenticationStrategy;

MySessionRegistryImpl sessionRegistry = new MySessionRegistryImpl();
MyConcurrentSessionControlAuthenticationStrategy strategy =
        new MyConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
```

## 9. Testing & Build

构建与测试：

```bash
mvn clean verify
```

- 测试资源包含 `SecurityConfiguration`（`@EnableWebSecurity`、方法级安全）作为装配模板；本模块暂无可执行单元测试，覆盖率以 JaCoCo 报告为准；
- 构建配置了 JaCoCo Maven 插件：覆盖率报告生成于 `target/site/jacoco/index.html`，并配置了 BUNDLE 行覆盖率 90% 的校验规则（`haltOnFailure=false`，即只报告不阻断构建）；
- `central` Maven Profile（`mvn -Pcentral deploy`）附加 GPG 签名、源码包与 Javadoc 包用于发布。

## 10. Versioning & Branches

维护三条并行版本线：

| 分支 | JDK | 版本模式 |
|:---|:---|:---|
| `feature/1.0.x` | 8 | `1.0.x.*` |
| `feature/2.0.x` | 17 | `2.0.x.*` |
| `feature/3.0.x` | 21 | `3.0.x.*` |

维护策略：`1.0.x` 为当前活跃开发线（当前快照 `1.0.x.20260630-SNAPSHOT`）；`2.0.x` 与 `3.0.x` 为面向更新 JDK 的前向移植线。快照按需构建，正式 Release 通过 GitHub Releases 分发。

## 11. Contributing & License

- Fork 仓库并提交 Pull Request；`1.0.x` 版本线保持 JDK 8 兼容；
- Bug 反馈与功能建议通过 GitHub Issues 跟踪；
- 基于 [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0) 开源。
