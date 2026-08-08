package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecuritySessionMgtPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecuritySessionMgtProperties props = new SecuritySessionMgtProperties();
        assertNotNull(props);
    }
}
