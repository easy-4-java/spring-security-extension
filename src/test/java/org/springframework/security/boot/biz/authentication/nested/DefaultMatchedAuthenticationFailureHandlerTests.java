package org.springframework.security.boot.biz.authentication.nested;

import org.junit.jupiter.api.Test;
import org.springframework.security.boot.biz.exception.*;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMatchedAuthenticationFailureHandlerTests {

    private final DefaultMatchedAuthenticationFailureHandler handler = new DefaultMatchedAuthenticationFailureHandler();

    @Test void shouldSupportMethodNotSupported() {
        assertTrue(handler.supports(new AuthenticationMethodNotSupportedException("msg")));
    }

    @Test void shouldSupportCaptchaExceptions() {
        assertTrue(handler.supports(new AuthenticationCaptchaNotFoundException("msg")));
        assertTrue(handler.supports(new AuthenticationCaptchaIncorrectException("msg")));
    }

    @Test void shouldSupportTokenExceptions() {
        assertTrue(handler.supports(new AuthenticationTokenNotFoundException("msg")));
        assertTrue(handler.supports(new AuthenticationTokenIncorrectException("msg")));
        assertTrue(handler.supports(new AuthenticationTokenExpiredException("msg")));
    }

    @Test void shouldNotSupportOtherExceptions() {
        assertFalse(handler.supports(new org.springframework.security.authentication.BadCredentialsException("msg")));
    }
}
