package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.boot.biz.authentication.AuthenticationListener;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListenedAuthenticationSuccessHandlerTests {

    @Test
    void shouldCreateWithDefaultTargetUrl() {
        ListenedAuthenticationSuccessHandler handler = new ListenedAuthenticationSuccessHandler("/");
        assertNotNull(handler);
        assertNull(handler.getAuthenticationListeners());
    }

    @Test
    void shouldCreateWithListeners() {
        List<AuthenticationListener> listeners = new ArrayList<>();
        ListenedAuthenticationSuccessHandler handler = new ListenedAuthenticationSuccessHandler(listeners, "/");
        assertNotNull(handler.getAuthenticationListeners());
    }

    @Test
    void shouldNotifyListenersOnSuccess() throws Exception {
        final boolean[] called = {false};
        AuthenticationListener listener = new AuthenticationListener() {
            public void onSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) { called[0] = true; }
            public void onFailure(HttpServletRequest req, HttpServletResponse res, org.springframework.security.core.AuthenticationException ae) {}
        };
        List<AuthenticationListener> listeners = new ArrayList<>();
        listeners.add(listener);
        ListenedAuthenticationSuccessHandler handler = new ListenedAuthenticationSuccessHandler(listeners, "/");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        handler.onAuthenticationSuccess(request, response, auth);
        assertTrue(called[0]);
    }
}
