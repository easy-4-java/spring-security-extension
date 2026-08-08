package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.boot.biz.authentication.AuthenticationListener;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListenedAuthenticationFailureHandlerTests {

    @Test
    void shouldCreateWithDefaultFailureUrl() {
        ListenedAuthenticationFailureHandler handler = new ListenedAuthenticationFailureHandler("/error");
        assertNotNull(handler);
        assertNull(handler.getAuthenticationListeners());
    }

    @Test
    void shouldCreateWithListeners() {
        List<AuthenticationListener> listeners = new ArrayList<>();
        ListenedAuthenticationFailureHandler handler = new ListenedAuthenticationFailureHandler(listeners, "/error");
        assertNotNull(handler.getAuthenticationListeners());
    }

    @Test
    void shouldSetAndGetListeners() {
        ListenedAuthenticationFailureHandler handler = new ListenedAuthenticationFailureHandler("/error");
        List<AuthenticationListener> listeners = new ArrayList<>();
        handler.setAuthenticationListeners(listeners);
        assertEquals(listeners, handler.getAuthenticationListeners());
    }

    @Test
    void shouldNotifyListenersOnFailure() throws Exception {
        final boolean[] called = {false};
        AuthenticationListener listener = new AuthenticationListener() {
            public void onSuccess(HttpServletRequest req, HttpServletResponse res, org.springframework.security.core.Authentication auth) {}
            public void onFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ae) { called[0] = true; }
        };
        List<AuthenticationListener> listeners = new ArrayList<>();
        listeners.add(listener);
        ListenedAuthenticationFailureHandler handler = new ListenedAuthenticationFailureHandler(listeners, "/error");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        org.springframework.security.authentication.BadCredentialsException ex =
                new org.springframework.security.authentication.BadCredentialsException("bad");
        handler.onAuthenticationFailure(request, response, ex);
        assertTrue(called[0]);
    }
}
