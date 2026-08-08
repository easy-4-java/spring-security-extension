package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactiveServerLogoutSuccessHandlerTests {

    @Test void shouldCreateInstance() {
        ReactiveServerLogoutSuccessHandler handler = new ReactiveServerLogoutSuccessHandler();
        assertNotNull(handler);
    }

    @Test void shouldHandleLogoutSuccess() {
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        when(exchange.getResponse()).thenReturn(response);
        when(response.getHeaders()).thenReturn(headers);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
        when(response.writeWith(any())).thenReturn(Mono.empty());

        WebFilterExchange wfExchange = mock(WebFilterExchange.class);
        when(wfExchange.getExchange()).thenReturn(exchange);

        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p");
        ReactiveServerLogoutSuccessHandler handler = new ReactiveServerLogoutSuccessHandler();
        StepVerifier.create(handler.onLogoutSuccess(wfExchange, auth))
                .verifyComplete();
    }
}
