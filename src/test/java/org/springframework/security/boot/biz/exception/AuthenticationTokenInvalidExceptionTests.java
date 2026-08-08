package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTokenInvalidExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationTokenInvalidException ex = new AuthenticationTokenInvalidException("invalid");
        assertEquals(10024, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationTokenInvalidException ex = new AuthenticationTokenInvalidException("invalid", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
