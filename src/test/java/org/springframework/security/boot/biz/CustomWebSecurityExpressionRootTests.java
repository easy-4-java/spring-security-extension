package org.springframework.security.boot.biz;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CustomWebSecurityExpressionRootTests {

    private FilterInvocation createFilterInvocation(MockHttpServletRequest request) {
        FilterChain noopChain = (req, res) -> {};
        return new FilterInvocation(request, new MockHttpServletResponse(), noopChain);
    }

    @Test
    void shouldCreateInstance() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        FilterInvocation fi = createFilterInvocation(request);
        CustomWebSecurityExpressionRoot root = new CustomWebSecurityExpressionRoot(auth, fi);
        assertNotNull(root.request);
    }

    @Test
    void shouldMatchIpAddress() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("192.168.1.100");
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        FilterInvocation fi = createFilterInvocation(request);
        CustomWebSecurityExpressionRoot root = new CustomWebSecurityExpressionRoot(auth, fi);
        assertTrue(root.hasIpAddress("192.168.1.100"));
        assertTrue(root.hasIpAddress("192.168.1.0/24"));
        assertFalse(root.hasIpAddress("10.0.0.1"));
    }

    @Test
    void shouldHandleNullRemoteAddr() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr(null);
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        FilterInvocation fi = createFilterInvocation(request);
        CustomWebSecurityExpressionRoot root = new CustomWebSecurityExpressionRoot(auth, fi);
        // null is treated as empty string which won't match
        assertFalse(root.hasIpAddress("192.168.1.1"));
    }
}
