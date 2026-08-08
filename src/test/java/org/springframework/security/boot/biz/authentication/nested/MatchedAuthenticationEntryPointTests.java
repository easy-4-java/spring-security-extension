package org.springframework.security.boot.biz.authentication.nested;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

class MatchedAuthenticationEntryPointTests {

    @Test void shouldSupportDefaultCommence() throws Exception {
        MatchedAuthenticationEntryPoint ep = e -> true;
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        assertDoesNotThrow(() -> ep.commence(req, res, new BadCredentialsException("test")));
    }

    @Test void shouldReturnFalseForUnsupported() {
        MatchedAuthenticationEntryPoint ep = e -> false;
        assertFalse(ep.supports(new BadCredentialsException("test")));
    }
}
