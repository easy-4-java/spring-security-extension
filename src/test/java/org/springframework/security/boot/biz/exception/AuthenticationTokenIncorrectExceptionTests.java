package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTokenIncorrectExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationTokenIncorrectException ex = new AuthenticationTokenIncorrectException("incorrect");
        assertEquals(10025, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationTokenIncorrectException ex = new AuthenticationTokenIncorrectException("incorrect", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
