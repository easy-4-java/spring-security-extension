package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationMethodNotSupportedExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationMethodNotSupportedException ex = new AuthenticationMethodNotSupportedException("GET not supported");
        assertEquals(10002, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationMethodNotSupportedException ex = new AuthenticationMethodNotSupportedException("GET not supported", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
