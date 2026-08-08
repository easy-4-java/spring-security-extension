package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderFrameOptionsPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetEnabled() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        props.setEnabled(false);
        assertFalse(props.isEnabled());
    }
}
