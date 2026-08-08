package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.boot.biz.authentication.nested.MatchedAuthenticationEntryPoint;
import org.springframework.security.boot.biz.exception.AuthenticationCaptchaNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestAuthenticationEntryPointTests {

    @Test void shouldCreateWithLoginUrlAndEntryPoints() {
        List<MatchedAuthenticationEntryPoint> eps = new ArrayList<>();
        PostRequestAuthenticationEntryPoint ep = new PostRequestAuthenticationEntryPoint("/login", eps);
        assertNotNull(ep.getEntryPoints());
    }

    @Test void shouldSetAndGetStateless() {
        PostRequestAuthenticationEntryPoint ep = new PostRequestAuthenticationEntryPoint("/login", new ArrayList<>());
        ep.setStateless(true);
        assertTrue(ep.isStateless());
    }

    @Test void shouldHandleStatelessRequestWithEmptyEntries() throws Exception {
        PostRequestAuthenticationEntryPoint ep = new PostRequestAuthenticationEntryPoint("/login", new ArrayList<>());
        ep.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        ep.commence(req, res, new BadCredentialsException("bad"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldHandleStatelessRequestWithMatchedEntryPoint() throws Exception {
        MatchedAuthenticationEntryPoint matched = e -> true;
        List<MatchedAuthenticationEntryPoint> eps = new ArrayList<>();
        eps.add(matched);
        PostRequestAuthenticationEntryPoint ep = new PostRequestAuthenticationEntryPoint("/login", eps);
        ep.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        ep.commence(req, res, new BadCredentialsException("bad"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldHandleStatelessRequestWithUnmatchedEntryPoint() throws Exception {
        MatchedAuthenticationEntryPoint matched = e -> false;
        List<MatchedAuthenticationEntryPoint> eps = new ArrayList<>();
        eps.add(matched);
        PostRequestAuthenticationEntryPoint ep = new PostRequestAuthenticationEntryPoint("/login", eps);
        ep.setStateless(true);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        MockHttpServletResponse res = new MockHttpServletResponse();
        ep.commence(req, res, new BadCredentialsException("bad"));
        assertEquals(200, res.getStatus());
    }

    @Test void shouldSetAndGetEntryPoints() {
        PostRequestAuthenticationEntryPoint ep = new PostRequestAuthenticationEntryPoint("/login", new ArrayList<>());
        List<MatchedAuthenticationEntryPoint> newEps = new ArrayList<>();
        ep.setEntryPoints(newEps);
        assertEquals(newEps, ep.getEntryPoints());
    }
}
