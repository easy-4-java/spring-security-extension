package org.springframework.security.extension.property.header;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderXssProtectionPropertiesTest {

    @Test
    void shouldDefaultEnabledToFalse() {
        HeaderXssProtectionProperties props = new HeaderXssProtectionProperties();
        assertFalse(props.isEnabled());
    }

    @Test
    void shouldDefaultBlockToFalse() {
        HeaderXssProtectionProperties props = new HeaderXssProtectionProperties();
        assertFalse(props.isBlock());
    }

    @Test
    void shouldSetEnabled() {
        HeaderXssProtectionProperties props = new HeaderXssProtectionProperties();
        props.setEnabled(true);
        assertTrue(props.isEnabled());
    }

    @Test
    void shouldSetBlock() {
        HeaderXssProtectionProperties props = new HeaderXssProtectionProperties();
        props.setBlock(true);
        assertTrue(props.isBlock());
    }

    @Test
    void shouldHaveToString() {
        HeaderXssProtectionProperties props = new HeaderXssProtectionProperties();
        assertNotNull(props.toString());
        assertTrue(props.toString().contains("HeaderXssProtectionProperties"));
    }
}
