package org.springframework.security.extension.property.header;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderFrameOptionsPropertiesTest {

    @Test
    void shouldDefaultEnabledToFalse() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        assertFalse(props.isEnabled());
    }

    @Test
    void shouldDefaultDenyToFalse() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        assertFalse(props.isDeny());
    }

    @Test
    void shouldDefaultSameOriginToFalse() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        assertFalse(props.isSameOrigin());
    }

    @Test
    void shouldSetEnabled() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        props.setEnabled(true);
        assertTrue(props.isEnabled());
    }

    @Test
    void shouldSetDeny() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        props.setDeny(true);
        assertTrue(props.isDeny());
    }

    @Test
    void shouldSetSameOrigin() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        props.setSameOrigin(true);
        assertTrue(props.isSameOrigin());
    }

    @Test
    void shouldHaveToString() {
        HeaderFrameOptionsProperties props = new HeaderFrameOptionsProperties();
        assertNotNull(props.toString());
        assertTrue(props.toString().contains("HeaderFrameOptionsProperties"));
    }
}
