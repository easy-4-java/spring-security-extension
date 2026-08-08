package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.security.boot.biz.exception.*;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMatchedServerAuthenticationEntryPointTests {

    @Test void shouldSupportKnownExceptions() {
        DefaultMatchedServerAuthenticationEntryPoint ep = new DefaultMatchedServerAuthenticationEntryPoint();
        assertTrue(ep.supports(new AuthenticationMethodNotSupportedException("msg")));
        assertTrue(ep.supports(new AuthenticationCaptchaNotFoundException("msg")));
        assertTrue(ep.supports(new AuthenticationCaptchaIncorrectException("msg")));
        assertTrue(ep.supports(new AuthenticationTokenNotFoundException("msg")));
        assertTrue(ep.supports(new AuthenticationTokenIncorrectException("msg")));
        assertTrue(ep.supports(new AuthenticationTokenExpiredException("msg")));
    }

    @Test void shouldNotSupportUnknownExceptions() {
        DefaultMatchedServerAuthenticationEntryPoint ep = new DefaultMatchedServerAuthenticationEntryPoint();
        assertFalse(ep.supports(new org.springframework.security.authentication.BadCredentialsException("msg")));
    }
}
