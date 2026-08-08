package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderXssProtectionTests {

    @Test void shouldCreateInstance() {
        HeaderXssProtectionProperties props = new HeaderXssProtectionProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetEnabled() {
        HeaderXssProtectionProperties props = new HeaderXssProtectionProperties();
        props.setEnabled(false);
        assertFalse(props.isEnabled());
    }
}
