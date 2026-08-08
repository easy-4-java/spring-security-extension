package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeadersPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityHeadersProperties props = new SecurityHeadersProperties();
        assertNotNull(props);
    }
}
