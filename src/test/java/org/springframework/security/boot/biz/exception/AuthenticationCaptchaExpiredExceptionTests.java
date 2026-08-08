package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationCaptchaExpiredExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationCaptchaExpiredException ex = new AuthenticationCaptchaExpiredException("expired");
        assertEquals(10005, ex.getCode());
        assertEquals("expired", ex.getMessage());
    }
    @Test void shouldCreateWithMessageAndCause() {
        RuntimeException cause = new RuntimeException();
        AuthenticationCaptchaExpiredException ex = new AuthenticationCaptchaExpiredException("expired", cause);
        assertEquals(cause, ex.getCause());
    }
}
