package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeaderCorsPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityHeaderCorsProperties props = new SecurityHeaderCorsProperties();
        assertNotNull(props);
    }
}
