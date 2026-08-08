package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TrustedRedirectStrategyTests {

    @Test
    void shouldCreateInstance() {
        TrustedRedirectStrategy strategy = new TrustedRedirectStrategy();
        assertNotNull(strategy);
    }

    @Test
    void shouldRedirectToTrustedUrl() throws Exception {
        TrustedRedirectStrategy strategy = new TrustedRedirectStrategy();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        strategy.sendRedirect(request, response, "/api/test");
        assertEquals("/api/test", response.getRedirectedUrl());
    }

    @Test
    void shouldRedirectToDefaultWhenNotTrusted() throws Exception {
        TrustedRedirectStrategy strategy = new TrustedRedirectStrategy();
        strategy.setTrustedRedirects(Collections.singletonList("/safe/**"));
        strategy.setDefaultRedirectUrl("/home");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        strategy.sendRedirect(request, response, "/evil/path");
        assertEquals("/home", response.getRedirectedUrl());
    }

    @Test
    void shouldAllowAllWhenEmptyTrustedList() throws Exception {
        TrustedRedirectStrategy strategy = new TrustedRedirectStrategy();
        strategy.setTrustedRedirects(Collections.emptyList());
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        strategy.sendRedirect(request, response, "/any/path");
        assertEquals("/any/path", response.getRedirectedUrl());
    }

    @Test
    void shouldSetDefaultRedirectUrl() {
        TrustedRedirectStrategy strategy = new TrustedRedirectStrategy();
        strategy.setDefaultRedirectUrl("/custom");
        assertNotNull(strategy);
    }
}
