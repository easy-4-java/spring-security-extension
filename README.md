# spring-security-extension

基于 Spring Security 官方组件的纯 Java 扩展层，不包含 Spring Boot 自动配置。

## Maven

```xml
<dependency>
  <groupId>io.github.hiwepy</groupId>
  <artifactId>spring-security-extension</artifactId>
  <version>2.0.x-SNAPSHOT</version>
</dependency>
```

## 版本线

| 分支 | 版本前缀 | JDK | 说明 |
|------|----------|-----|------|
| `feature/1.0.x` | `1.0.x.*` | 8 | 对齐 Boot 2.x / Spring Security 5.x |
| `feature/2.0.x` | `2.0.x.*` | 17 | 对齐 Boot 3.x / Spring Security 6.x |
| `feature/3.0.x` | `3.0.x.*` | 21 | 对齐 Boot 4.x / Spring Security 7.x |

## 模块边界

- 保留认证、会话、异常、响应、工具类、Reactive 适配等纯 Java 扩展
- 不依赖 `spring-boot-starter-*`
- 如需 Redis 相关 API，仅依赖 `spring-data-redis` 基础库，不引入 Spring Boot Starter

## License

Apache License 2.0
