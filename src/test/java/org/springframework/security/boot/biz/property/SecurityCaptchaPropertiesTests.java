package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityCaptchaPropertiesTests {

    @Test
    void shouldCreateInstance() {
        SecurityCaptchaProperties props = new SecurityCaptchaProperties();
        assertNotNull(props);
    }
}
