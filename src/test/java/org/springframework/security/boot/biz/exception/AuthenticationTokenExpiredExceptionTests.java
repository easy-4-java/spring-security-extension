package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTokenExpiredExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationTokenExpiredException ex = new AuthenticationTokenExpiredException("expired");
        assertEquals(10023, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationTokenExpiredException ex = new AuthenticationTokenExpiredException("expired", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
