package org.springframework.security.boot.biz.session;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionAuthenticationFailureHandlerTests {

    @Test void shouldBeInterface() {
        // SessionAuthenticationFailureHandler is an interface
        assertTrue(SessionAuthenticationFailureHandler.class.isInterface());
    }
}
