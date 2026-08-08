package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatchedServerAuthenticationFailureHandlerTests {

    @Test void shouldSupportAndHandleFailure() {
        MatchedServerAuthenticationFailureHandler h = e -> true;
        assertTrue(h.supports(new BadCredentialsException("bad")));

        ServerWebExchange exchange = mock(ServerWebExchange.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        when(exchange.getResponse()).thenReturn(response);
        when(response.getHeaders()).thenReturn(headers);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
        when(response.writeWith(any())).thenReturn(Mono.empty());

        WebFilterExchange wfExchange = mock(WebFilterExchange.class);
        when(wfExchange.getExchange()).thenReturn(exchange);

        StepVerifier.create(h.onAuthenticationFailure(wfExchange, new BadCredentialsException("bad")))
                .verifyComplete();
    }
}
