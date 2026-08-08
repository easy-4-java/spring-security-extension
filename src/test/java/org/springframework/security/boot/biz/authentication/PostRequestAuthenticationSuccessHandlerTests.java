package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.boot.biz.authentication.nested.MatchedAuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestAuthenticationSuccessHandlerTests {

    @Test void shouldCreateWithSuccessHandlers() {
        List<MatchedAuthenticationSuccessHandler> handlers = new ArrayList<>();
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(handlers);
        assertNotNull(h.getSuccessHandlers());
    }

    @Test void shouldCreateWithListenersAndHandlers() {
        List<AuthenticationListener> listeners = new ArrayList<>();
        List<MatchedAuthenticationSuccessHandler> handlers = new ArrayList<>();
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(listeners, handlers);
        assertNotNull(h.getAuthenticationListeners());
        assertNotNull(h.getSuccessHandlers());
    }

    @Test void shouldSetAndGetStateless() {
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(new ArrayList<>());
        h.setStateless(true);
        assertTrue(h.isStateless());
    }

    @Test void shouldHandleStatelessWithEmptyHandlers() throws Exception {
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(new ArrayList<>());
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationSuccess(req, res, new UsernamePasswordAuthenticationToken("u", "p"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldHandleStatelessWithMatchedHandler() throws Exception {
        MatchedAuthenticationSuccessHandler matched = a -> true;
        List<MatchedAuthenticationSuccessHandler> handlers = new ArrayList<>();
        handlers.add(matched);
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(handlers);
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationSuccess(req, res, new UsernamePasswordAuthenticationToken("u", "p"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldHandleStatelessWithUnmatchedHandler() throws Exception {
        MatchedAuthenticationSuccessHandler matched = a -> false;
        List<MatchedAuthenticationSuccessHandler> handlers = new ArrayList<>();
        handlers.add(matched);
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(handlers);
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationSuccess(req, res, new UsernamePasswordAuthenticationToken("u", "p"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldNotifyListenersOnSuccess() throws Exception {
        final boolean[] called = {false};
        AuthenticationListener listener = new AuthenticationListener() {
            public void onSuccess(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse res, org.springframework.security.core.Authentication auth) { called[0] = true; }
            public void onFailure(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse res, org.springframework.security.core.AuthenticationException ae) {}
        };
        List<AuthenticationListener> listeners = new ArrayList<>();
        listeners.add(listener);
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(listeners, new ArrayList<>());
        h.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        h.onAuthenticationSuccess(req, res, new UsernamePasswordAuthenticationToken("u", "p"));
        assertTrue(called[0]);
    }

    @Test void shouldSetAndGetMessages() {
        PostRequestAuthenticationSuccessHandler h = new PostRequestAuthenticationSuccessHandler(new ArrayList<>());
        assertNotNull(h.getMessages());
    }
}
