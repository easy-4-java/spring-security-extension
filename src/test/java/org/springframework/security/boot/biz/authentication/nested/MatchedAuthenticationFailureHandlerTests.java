package org.springframework.security.boot.biz.authentication.nested;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;

class MatchedAuthenticationFailureHandlerTests {

    @Test void shouldSupportDefaultOnFailure() throws Exception {
        MatchedAuthenticationFailureHandler h = e -> true;
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        assertDoesNotThrow(() -> h.onAuthenticationFailure(req, res, new BadCredentialsException("test")));
    }
}
