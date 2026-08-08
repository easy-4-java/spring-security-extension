package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTokenNotFoundExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationTokenNotFoundException ex = new AuthenticationTokenNotFoundException("missing");
        assertEquals(10022, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationTokenNotFoundException ex = new AuthenticationTokenNotFoundException("missing", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
