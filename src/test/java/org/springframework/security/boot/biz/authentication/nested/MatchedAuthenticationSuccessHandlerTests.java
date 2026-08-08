package org.springframework.security.boot.biz.authentication.nested;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class MatchedAuthenticationSuccessHandlerTests {

    @Test void shouldSupportDefaultOnSuccess() throws Exception {
        MatchedAuthenticationSuccessHandler h = a -> true;
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p");
        assertDoesNotThrow(() -> h.onAuthenticationSuccess(req, res, auth));
    }

    @Test void shouldReturnFalseForUnsupported() {
        MatchedAuthenticationSuccessHandler h = a -> false;
        Authentication auth = new UsernamePasswordAuthenticationToken("u", "p");
        assertFalse(h.supports(auth));
    }
}
