package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.boot.biz.exception.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReactiveSecurityResponseUtilsTests {

    private ServerHttpResponse mockResponse() {
        ServerHttpResponse response = mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        when(response.getHeaders()).thenReturn(headers);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
        when(response.writeWith(any())).thenReturn(Mono.empty());
        return response;
    }

    @Test void shouldHandleSuccess() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p");
        StepVerifier.create(ReactiveSecurityResponseUtils.handleSuccess(request, response, auth))
                .verifyComplete();
    }

    @Test void shouldHandleCaptchaExpiredException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new AuthenticationCaptchaExpiredException("expired")))
                .verifyComplete();
    }

    @Test void shouldHandleUsernameNotFoundException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new UsernameNotFoundException("not found")))
                .verifyComplete();
    }

    @Test void shouldHandleBadCredentialsException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new BadCredentialsException("bad")))
                .verifyComplete();
    }

    @Test void shouldHandleDisabledException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new DisabledException("disabled")))
                .verifyComplete();
    }

    @Test void shouldHandleLockedException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new LockedException("locked")))
                .verifyComplete();
    }

    @Test void shouldHandleAccountExpiredException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new AccountExpiredException("expired")))
                .verifyComplete();
    }

    @Test void shouldHandleCredentialsExpiredException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new CredentialsExpiredException("expired")))
                .verifyComplete();
    }

    @Test void shouldHandleGenericException() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mockResponse();
        StepVerifier.create(ReactiveSecurityResponseUtils.handleFailure(request, response, new AuthenticationException("generic") {}))
                .verifyComplete();
    }
}
