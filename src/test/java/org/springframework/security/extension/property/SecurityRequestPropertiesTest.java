package org.springframework.security.extension.property;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityRequestPropertiesTest {

    @Test
    void shouldInstantiate() {
        SecurityRequestProperties props = new SecurityRequestProperties();
        assertNotNull(props);
    }
}
