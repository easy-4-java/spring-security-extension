package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.security.boot.biz.exception.*;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMatchedServerAuthenticationFailureHandlerTests {

    @Test void shouldSupportKnownExceptions() {
        DefaultMatchedServerAuthenticationFailureHandler handler = new DefaultMatchedServerAuthenticationFailureHandler();
        assertTrue(handler.supports(new AuthenticationMethodNotSupportedException("msg")));
        assertTrue(handler.supports(new AuthenticationCaptchaNotFoundException("msg")));
        assertTrue(handler.supports(new AuthenticationCaptchaIncorrectException("msg")));
        assertTrue(handler.supports(new AuthenticationTokenNotFoundException("msg")));
        assertTrue(handler.supports(new AuthenticationTokenIncorrectException("msg")));
        assertTrue(handler.supports(new AuthenticationTokenExpiredException("msg")));
    }

    @Test void shouldNotSupportUnknownExceptions() {
        DefaultMatchedServerAuthenticationFailureHandler handler = new DefaultMatchedServerAuthenticationFailureHandler();
        assertFalse(handler.supports(new org.springframework.security.authentication.BadCredentialsException("msg")));
    }
}
