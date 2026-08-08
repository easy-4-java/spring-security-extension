package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityRememberMePropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityRememberMeProperties props = new SecurityRememberMeProperties();
        assertNotNull(props);
    }
}
