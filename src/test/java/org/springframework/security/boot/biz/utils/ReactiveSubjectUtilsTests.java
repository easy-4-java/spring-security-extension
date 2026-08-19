package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ReactiveSubjectUtilsTests {

    @Test
    void shouldGetSecurityContext() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        SecurityContext context = new SecurityContextImpl(auth);

        Mono<SecurityContext> result = ReactiveSubjectUtils.getSecurityContext()
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

        StepVerifier.create(result)
                .expectNext(context)
                .verifyComplete();
    }

    @Test
    void shouldGetAuthentication() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        SecurityContext context = new SecurityContextImpl(auth);

        Mono<Authentication> result = ReactiveSubjectUtils.getAuthentication()
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

        StepVerifier.create(result)
                .expectNext(auth)
                .verifyComplete();
    }

    @Test
    void shouldGetPrincipal() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        SecurityContext context = new SecurityContextImpl(auth);

        Mono<String> result = ReactiveSubjectUtils.getPrincipal(String.class)
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

        StepVerifier.create(result)
                .expectNext("user")
                .verifyComplete();
    }

    @Test
    void shouldGetPrincipalAsObject() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        SecurityContext context = new SecurityContextImpl(auth);

        Mono<Object> result = ReactiveSubjectUtils.getPrincipal()
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

        StepVerifier.create(result)
                .expectNext("user")
                .verifyComplete();
    }

    @Test
    void shouldCheckIsAssignableFrom() {
        assertTrue(ReactiveSubjectUtils.isAssignableFrom(String.class, CharSequence.class));
        assertFalse(ReactiveSubjectUtils.isAssignableFrom(Integer.class, String.class));
        assertFalse(ReactiveSubjectUtils.isAssignableFrom(null, String.class));
        assertFalse(ReactiveSubjectUtils.isAssignableFrom(String.class, (Class<?>[]) null));
    }
}
