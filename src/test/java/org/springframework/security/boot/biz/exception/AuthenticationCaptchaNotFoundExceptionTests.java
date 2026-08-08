package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationCaptchaNotFoundExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationCaptchaNotFoundException ex = new AuthenticationCaptchaNotFoundException("missing");
        assertEquals(10004, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationCaptchaNotFoundException ex = new AuthenticationCaptchaNotFoundException("missing", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
