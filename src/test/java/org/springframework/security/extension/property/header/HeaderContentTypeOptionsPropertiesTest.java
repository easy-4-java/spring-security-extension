package org.springframework.security.extension.property.header;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderContentTypeOptionsPropertiesTest {

    @Test
    void shouldDefaultEnabledToFalse() {
        HeaderContentTypeOptionsProperties props = new HeaderContentTypeOptionsProperties();
        assertFalse(props.isEnabled());
    }

    @Test
    void shouldSetEnabled() {
        HeaderContentTypeOptionsProperties props = new HeaderContentTypeOptionsProperties();
        props.setEnabled(true);
        assertTrue(props.isEnabled());
    }

    @Test
    void shouldHaveToString() {
        HeaderContentTypeOptionsProperties props = new HeaderContentTypeOptionsProperties();
        assertNotNull(props.toString());
        assertTrue(props.toString().contains("HeaderContentTypeOptionsProperties"));
    }
}
