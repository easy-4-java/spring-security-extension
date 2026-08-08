package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WebfluxUtilsTests {

    @Test void shouldDetectPostRequest() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        when(request.getMethod()).thenReturn(HttpMethod.POST);
        assertTrue(WebfluxUtils.isPostRequest(request));
    }

    @Test void shouldNotDetectGetAsPost() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        when(request.getMethod()).thenReturn(HttpMethod.GET);
        assertFalse(WebfluxUtils.isPostRequest(request));
    }

    @Test void shouldDetectAjaxRequest() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Requested-With", "XMLHttpRequest");
        when(request.getHeaders()).thenReturn(headers);
        assertTrue(WebfluxUtils.isAjaxRequest(request));
    }

    @Test void shouldDetectJsonContentType() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        when(request.getHeaders()).thenReturn(headers);
        assertTrue(WebfluxUtils.isContentTypeJson(request));
    }

    @Test void shouldDetectObjectRequest() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        when(request.getHeaders()).thenReturn(headers);
        when(request.getMethod()).thenReturn(HttpMethod.POST);
        assertTrue(WebfluxUtils.isObjectRequest(request));
    }

    @Test void shouldDetectAjaxResponse() {
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Requested-With", "XMLHttpRequest");
        when(request.getHeaders()).thenReturn(headers);
        assertTrue(WebfluxUtils.isAjaxResponse(request));
    }
}
