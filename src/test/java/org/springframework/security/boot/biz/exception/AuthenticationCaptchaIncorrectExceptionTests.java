package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationCaptchaIncorrectExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationCaptchaIncorrectException ex = new AuthenticationCaptchaIncorrectException("wrong");
        assertEquals(10006, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationCaptchaIncorrectException ex = new AuthenticationCaptchaIncorrectException("wrong", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
