package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.boot.biz.exception.AuthenticationCaptchaNotFoundException;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactiveAuthenticationEntryPointTests {

    @Test void shouldCreateWithEntryPoints() {
        List<MatchedServerAuthenticationEntryPoint> eps = Collections.emptyList();
        ReactiveAuthenticationEntryPoint ep = new ReactiveAuthenticationEntryPoint(eps);
        assertNotNull(ep.getEntryPoints());
    }

    @Test void shouldSetAndGetEntryPoints() {
        ReactiveAuthenticationEntryPoint ep = new ReactiveAuthenticationEntryPoint(Collections.emptyList());
        List<MatchedServerAuthenticationEntryPoint> newEps = Collections.singletonList(e -> true);
        ep.setEntryPoints(newEps);
        assertEquals(1, ep.getEntryPoints().size());
    }

    @Test void shouldHandleWithEmptyEntryPoints() {
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        when(exchange.getRequest()).thenReturn(request);
        when(exchange.getResponse()).thenReturn(response);
        when(response.getHeaders()).thenReturn(headers);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
        when(response.writeWith(any())).thenReturn(Mono.empty());

        ReactiveAuthenticationEntryPoint ep = new ReactiveAuthenticationEntryPoint(Collections.emptyList());
        StepVerifier.create(ep.commence(exchange, new BadCredentialsException("bad")))
                .verifyComplete();
    }

    @Test void shouldDelegateToMatchedEntryPoint() {
        MatchedServerAuthenticationEntryPoint matched = mock(MatchedServerAuthenticationEntryPoint.class);
        when(matched.supports(any())).thenReturn(true);
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        when(matched.commence(any(), any())).thenReturn(Mono.empty());

        ReactiveAuthenticationEntryPoint ep = new ReactiveAuthenticationEntryPoint(Collections.singletonList(matched));
        StepVerifier.create(ep.commence(exchange, new BadCredentialsException("bad")))
                .verifyComplete();
    }

    @Test void shouldHandleUnmatchedEntryPoint() {
        MatchedServerAuthenticationEntryPoint matched = mock(MatchedServerAuthenticationEntryPoint.class);
        when(matched.supports(any())).thenReturn(false);

        ServerWebExchange exchange = mock(ServerWebExchange.class);
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        when(exchange.getRequest()).thenReturn(request);
        when(exchange.getResponse()).thenReturn(response);
        when(response.getHeaders()).thenReturn(headers);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
        when(response.writeWith(any())).thenReturn(Mono.empty());

        ReactiveAuthenticationEntryPoint ep = new ReactiveAuthenticationEntryPoint(Collections.singletonList(matched));
        StepVerifier.create(ep.commence(exchange, new BadCredentialsException("bad")))
                .verifyComplete();
    }

    @Test void shouldSetAndGetMessages() {
        ReactiveAuthenticationEntryPoint ep = new ReactiveAuthenticationEntryPoint(Collections.emptyList());
        assertNotNull(ep.getMessages());
    }
}
