package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactiveAuthenticationSuccessHandlerTests {

    @Test void shouldCreateWithHandlers() {
        ReactiveAuthenticationSuccessHandler h = new ReactiveAuthenticationSuccessHandler(Collections.emptyList());
        assertNotNull(h.getSuccessHandlers());
    }

    @Test void shouldHandleWithEmptyHandlers() {
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        when(exchange.getRequest()).thenReturn(request);
        when(exchange.getResponse()).thenReturn(response);
        when(response.getHeaders()).thenReturn(headers);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
        when(response.writeWith(any())).thenReturn(Mono.empty());

        WebFilterExchange wfExchange = mock(WebFilterExchange.class);
        when(wfExchange.getExchange()).thenReturn(exchange);

        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p");
        ReactiveAuthenticationSuccessHandler h = new ReactiveAuthenticationSuccessHandler(Collections.emptyList());
        StepVerifier.create(h.onAuthenticationSuccess(wfExchange, auth))
                .verifyComplete();
    }

    @Test void shouldDelegateToMatchedHandler() {
        MatchedServerAuthenticationSuccessHandler matched = mock(MatchedServerAuthenticationSuccessHandler.class);
        when(matched.supports(any())).thenReturn(true);
        when(matched.onAuthenticationSuccess(any(), any())).thenReturn(Mono.empty());

        ServerWebExchange exchange = mock(ServerWebExchange.class);
        WebFilterExchange wfExchange = mock(WebFilterExchange.class);
        when(wfExchange.getExchange()).thenReturn(exchange);

        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p");
        ReactiveAuthenticationSuccessHandler h = new ReactiveAuthenticationSuccessHandler(Collections.singletonList(matched));
        StepVerifier.create(h.onAuthenticationSuccess(wfExchange, auth))
                .verifyComplete();
    }

    @Test void shouldHandleUnmatchedHandler() {
        MatchedServerAuthenticationSuccessHandler matched = mock(MatchedServerAuthenticationSuccessHandler.class);
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

        WebFilterExchange wfExchange = mock(WebFilterExchange.class);
        when(wfExchange.getExchange()).thenReturn(exchange);

        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p");
        ReactiveAuthenticationSuccessHandler h = new ReactiveAuthenticationSuccessHandler(Collections.singletonList(matched));
        StepVerifier.create(h.onAuthenticationSuccess(wfExchange, auth))
                .verifyComplete();
    }
}
