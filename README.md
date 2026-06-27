# spring-security-extension

Spring Security Extensions - independent of Spring Boot, fully compatible with JDK 1.8.

## Features

- **Authentication**: POST-based JSON authentication filter, authentication providers, success/failure handlers
- **Captcha**: Captcha resolver interface with session-based and null implementations
- **Session Management**: Concurrent session control, Redis-backed session registry
- **Exception Handling**: Structured auth response codes and i18n error messages
- **Reactive (WebFlux)**: Entry points, success/failure handlers, JWT web filter for reactive applications
- **Security Headers**: CORS, CSRF, HSTS, Content-Security-Policy and more
- **Utilities**: Security response helpers, subject utilities, web security configuration helpers

## Requirements

- JDK 1.8+
- Spring Security 5.7.x

## Maven

```xml
<dependency>
    <groupId>io.github.hiwepy</groupId>
    <artifactId>spring-security-extension</artifactId>
    <version>1.0.x-SNAPSHOT</version>
</dependency>
```

## Package Structure

```
org.springframework.security.boot
├── biz/
│   ├── authentication/       # Authentication filters, providers, handlers
│   │   ├── captcha/          # Captcha support
│   │   ├── nested/           # Matched entry points and handlers (Servlet)
│   │   └── server/           # Reactive (WebFlux) handlers
│   ├── exception/            # Auth exceptions and response models
│   ├── filter/               # CORS and HTTP params filters
│   ├── property/             # Configuration properties (POJOs)
│   │   └── header/           # Security header properties
│   ├── session/              # Session management
│   └── userdetails/          # User details and JWT support
└── utils/                    # Security and web utilities
```

## License

[Apache License 2.0](LICENSE)
