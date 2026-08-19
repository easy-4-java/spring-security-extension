package org.springframework.security.extension.property.header;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderCacheControlPropertiesTest {

    @Test
    void shouldDefaultEnabledToFalse() {
        HeaderCacheControlProperties props = new HeaderCacheControlProperties();
        assertFalse(props.isEnabled());
    }

    @Test
    void shouldSetEnabled() {
        HeaderCacheControlProperties props = new HeaderCacheControlProperties();
        props.setEnabled(true);
        assertTrue(props.isEnabled());
    }

    @Test
    void shouldHaveToString() {
        HeaderCacheControlProperties props = new HeaderCacheControlProperties();
        assertNotNull(props.toString());
        assertTrue(props.toString().contains("HeaderCacheControlProperties"));
    }
}
