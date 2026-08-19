package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CustomWebSecurityExpressionRootTests {

    @Test
    void shouldCreateInstance() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        Supplier<Authentication> authSupplier = () -> auth;
        CustomWebSecurityExpressionRoot root = new CustomWebSecurityExpressionRoot(authSupplier, request);
        assertNotNull(root.request);
    }

    @Test
    void shouldMatchIpAddress() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        Supplier<Authentication> authSupplier = () -> auth;
        CustomWebSecurityExpressionRoot root = new CustomWebSecurityExpressionRoot(authSupplier, request);
        assertTrue(root.hasIpAddress("192.168.1.100"));
        assertTrue(root.hasIpAddress("192.168.1.0/24"));
        assertFalse(root.hasIpAddress("10.0.0.1"));
    }

    @Test
    void shouldHandleNullRemoteAddr() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr(null);
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        Supplier<Authentication> authSupplier = () -> auth;
        CustomWebSecurityExpressionRoot root = new CustomWebSecurityExpressionRoot(authSupplier, request);
        // null is treated as empty string which won't match
        assertFalse(root.hasIpAddress("192.168.1.1"));
    }
}
