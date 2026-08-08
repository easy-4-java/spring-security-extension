package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityRequestPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityRequestProperties props = new SecurityRequestProperties();
        assertNotNull(props);
    }
}
