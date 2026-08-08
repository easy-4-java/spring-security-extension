package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactiveServerAccessDeniedHandlerTests {

    @Test void shouldCreateInstance() {
        ReactiveServerAccessDeniedHandler handler = new ReactiveServerAccessDeniedHandler();
        assertNotNull(handler);
    }

    @Test void shouldHandleAccessDenied() {
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        when(exchange.getResponse()).thenReturn(response);
        when(response.getHeaders()).thenReturn(headers);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
        when(response.writeWith(any())).thenReturn(Mono.empty());

        ReactiveServerAccessDeniedHandler handler = new ReactiveServerAccessDeniedHandler();
        StepVerifier.create(handler.handle(exchange, new AccessDeniedException("denied")))
                .verifyComplete();
    }
}
