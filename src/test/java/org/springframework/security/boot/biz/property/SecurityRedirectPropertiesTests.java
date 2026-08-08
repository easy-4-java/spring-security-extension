package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityRedirectPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityRedirectProperties props = new SecurityRedirectProperties();
        assertNotNull(props);
    }
}
