package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityEntryPointPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        assertNotNull(props);
    }
}
