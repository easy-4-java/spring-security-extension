package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class RemoteAddrUtilsTests {

    @Test void shouldGetRemoteAddrDirectly() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.1");
        assertEquals("192.168.1.1", RemoteAddrUtils.getRemoteAddr(request));
    }

    @Test void shouldResolveLocalhostTo127() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("localhost");
        assertEquals("127.0.0.1", RemoteAddrUtils.getRemoteAddr(request));
    }

    @Test void shouldUseXRealIpHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("10.0.0.1");
        request.addHeader("X-Real-IP", "203.0.113.1");
        assertEquals("203.0.113.1", RemoteAddrUtils.getRemoteAddr(request));
    }

    @Test void shouldSkipUnknownHeaderValues() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("10.0.0.1");
        request.addHeader("X-Real-IP", "unknown");
        assertEquals("10.0.0.1", RemoteAddrUtils.getRemoteAddr(request));
    }

    @Test void shouldUseCdnSrcIpHeader() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("10.0.0.1");
        request.addHeader("Cdn-Src-Ip", "198.51.100.1");
        assertEquals("198.51.100.1", RemoteAddrUtils.getRemoteAddr(request));
    }
}
