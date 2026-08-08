package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactiveAuthenticationFailureHandlerTests {

    @Test void shouldCreateWithHandlers() {
        ReactiveAuthenticationFailureHandler h = new ReactiveAuthenticationFailureHandler(Collections.emptyList());
        assertNotNull(h.getFailureHandlers());
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

        ReactiveAuthenticationFailureHandler h = new ReactiveAuthenticationFailureHandler(Collections.emptyList());
        StepVerifier.create(h.onAuthenticationFailure(wfExchange, new BadCredentialsException("bad")))
                .verifyComplete();
    }

    @Test void shouldDelegateToMatchedHandler() {
        MatchedServerAuthenticationFailureHandler matched = mock(MatchedServerAuthenticationFailureHandler.class);
        when(matched.supports(any())).thenReturn(true);

        ServerWebExchange exchange = mock(ServerWebExchange.class);
        WebFilterExchange wfExchange = mock(WebFilterExchange.class);
        when(wfExchange.getExchange()).thenReturn(exchange);

        ReactiveAuthenticationFailureHandler h = new ReactiveAuthenticationFailureHandler(Collections.singletonList(matched));
        StepVerifier.create(h.onAuthenticationFailure(wfExchange, new BadCredentialsException("bad")))
                .verifyComplete();
    }

    @Test void shouldHandleUnmatchedHandler() {
        MatchedServerAuthenticationFailureHandler matched = mock(MatchedServerAuthenticationFailureHandler.class);
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

        ReactiveAuthenticationFailureHandler h = new ReactiveAuthenticationFailureHandler(Collections.singletonList(matched));
        StepVerifier.create(h.onAuthenticationFailure(wfExchange, new BadCredentialsException("bad")))
                .verifyComplete();
    }
}
