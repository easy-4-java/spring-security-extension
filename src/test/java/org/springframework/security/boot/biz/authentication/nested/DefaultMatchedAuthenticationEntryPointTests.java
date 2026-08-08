package org.springframework.security.boot.biz.authentication.nested;

import org.junit.jupiter.api.Test;
import org.springframework.security.boot.biz.exception.*;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMatchedAuthenticationEntryPointTests {

    private final DefaultMatchedAuthenticationEntryPoint entryPoint = new DefaultMatchedAuthenticationEntryPoint();

    @Test void shouldSupportMethodNotSupported() {
        assertTrue(entryPoint.supports(new AuthenticationMethodNotSupportedException("msg")));
    }

    @Test void shouldSupportCaptchaNotFound() {
        assertTrue(entryPoint.supports(new AuthenticationCaptchaNotFoundException("msg")));
    }

    @Test void shouldSupportCaptchaIncorrect() {
        assertTrue(entryPoint.supports(new AuthenticationCaptchaIncorrectException("msg")));
    }

    @Test void shouldSupportTokenNotFound() {
        assertTrue(entryPoint.supports(new AuthenticationTokenNotFoundException("msg")));
    }

    @Test void shouldSupportTokenIncorrect() {
        assertTrue(entryPoint.supports(new AuthenticationTokenIncorrectException("msg")));
    }

    @Test void shouldSupportTokenExpired() {
        assertTrue(entryPoint.supports(new AuthenticationTokenExpiredException("msg")));
    }

    @Test void shouldNotSupportOtherExceptions() {
        assertFalse(entryPoint.supports(new org.springframework.security.authentication.BadCredentialsException("msg")));
    }
}
