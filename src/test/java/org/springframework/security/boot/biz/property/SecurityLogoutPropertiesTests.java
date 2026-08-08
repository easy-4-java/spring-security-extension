package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityLogoutPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        assertNotNull(props);
    }
}
