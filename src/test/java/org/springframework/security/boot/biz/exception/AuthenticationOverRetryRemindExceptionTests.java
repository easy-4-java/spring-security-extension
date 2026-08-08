package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationOverRetryRemindExceptionTests {
    @Test void shouldCreateWithMessage() {
        AuthenticationOverRetryRemindException ex = new AuthenticationOverRetryRemindException("over retry");
        assertEquals(10003, ex.getCode());
    }
    @Test void shouldCreateWithMessageAndCause() {
        AuthenticationOverRetryRemindException ex = new AuthenticationOverRetryRemindException("over retry", new RuntimeException());
        assertNotNull(ex.getCause());
    }
}
