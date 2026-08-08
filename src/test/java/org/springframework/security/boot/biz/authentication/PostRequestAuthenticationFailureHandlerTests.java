package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.boot.biz.authentication.nested.MatchedAuthenticationFailureHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestAuthenticationFailureHandlerTests {

    @Test void shouldCreateWithFailureHandlers() {
        List<MatchedAuthenticationFailureHandler> handlers = new ArrayList<>();
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(handlers);
        assertNotNull(h.getFailureHandlers());
    }

    @Test void shouldCreateWithListenersAndHandlers() {
        List<AuthenticationListener> listeners = new ArrayList<>();
        List<MatchedAuthenticationFailureHandler> handlers = new ArrayList<>();
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(listeners, handlers);
        assertNotNull(h.getAuthenticationListeners());
        assertNotNull(h.getFailureHandlers());
    }

    @Test void shouldSetAndGetStateless() {
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(new ArrayList<>());
        h.setStateless(true);
        assertTrue(h.isStateless());
    }

    @Test void shouldHandleStatelessWithEmptyHandlers() throws Exception {
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(new ArrayList<>());
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationFailure(req, res, new BadCredentialsException("bad"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldHandleStatelessWithMatchedHandler() throws Exception {
        MatchedAuthenticationFailureHandler matched = e -> true;
        List<MatchedAuthenticationFailureHandler> handlers = new ArrayList<>();
        handlers.add(matched);
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(handlers);
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationFailure(req, res, new BadCredentialsException("bad"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldHandleStatelessWithUnmatchedHandler() throws Exception {
        MatchedAuthenticationFailureHandler matched = e -> false;
        List<MatchedAuthenticationFailureHandler> handlers = new ArrayList<>();
        handlers.add(matched);
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(handlers);
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationFailure(req, res, new BadCredentialsException("bad"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldNotifyListenersOnFailure() throws Exception {
        final boolean[] called = {false};
        AuthenticationListener listener = new AuthenticationListener() {
            public void onSuccess(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse res, org.springframework.security.core.Authentication auth) {}
            public void onFailure(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse res, org.springframework.security.core.AuthenticationException ae) { called[0] = true; }
        };
        List<AuthenticationListener> listeners = new ArrayList<>();
        listeners.add(listener);
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(listeners, new ArrayList<>());
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationFailure(req, res, new BadCredentialsException("bad"));
        assertTrue(called[0]);
    }

    @Test void shouldSetAndGetListenersAndHandlers() {
        PostRequestAuthenticationFailureHandler h = new PostRequestAuthenticationFailureHandler(new ArrayList<>());
        List<AuthenticationListener> listeners = new ArrayList<>();
        h.setAuthenticationListeners(listeners);
        assertEquals(listeners, h.getAuthenticationListeners());
        List<MatchedAuthenticationFailureHandler> handlers = new ArrayList<>();
        h.setFailureHandlers(handlers);
        assertEquals(handlers, h.getFailureHandlers());
    }
}
