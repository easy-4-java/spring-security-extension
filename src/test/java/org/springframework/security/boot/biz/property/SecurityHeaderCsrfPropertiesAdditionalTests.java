package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeaderCsrfPropertiesAdditionalTests {

    @Test void shouldSetAndGetEnabled() {
        SecurityHeaderCsrfProperties props = new SecurityHeaderCsrfProperties();
        props.setEnabled(false);
        assertFalse(props.isEnabled());
    }
}
