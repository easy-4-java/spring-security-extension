package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityFailureRetryPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityFailureRetryProperties props = new SecurityFailureRetryProperties();
        assertNotNull(props);
    }
}
