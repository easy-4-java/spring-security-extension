package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeaderCsrfPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityHeaderCsrfProperties props = new SecurityHeaderCsrfProperties();
        assertNotNull(props);
    }
}
