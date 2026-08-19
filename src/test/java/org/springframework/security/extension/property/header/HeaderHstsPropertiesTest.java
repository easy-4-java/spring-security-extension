package org.springframework.security.extension.property.header;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderHstsPropertiesTest {

    @Test
    void shouldDefaultEnabledToFalse() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        assertFalse(props.isEnabled());
    }

    @Test
    void shouldDefaultIncludeSubDomainsToFalse() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        assertFalse(props.isIncludeSubDomains());
    }

    @Test
    void shouldDefaultMaxAgeInSecondsToZero() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        assertEquals(0L, props.getMaxAgeInSeconds());
    }

    @Test
    void shouldSetEnabled() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        props.setEnabled(true);
        assertTrue(props.isEnabled());
    }

    @Test
    void shouldSetIncludeSubDomains() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        props.setIncludeSubDomains(true);
        assertTrue(props.isIncludeSubDomains());
    }

    @Test
    void shouldSetMaxAgeInSeconds() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        props.setMaxAgeInSeconds(31536000L);
        assertEquals(31536000L, props.getMaxAgeInSeconds());
    }

    @Test
    void shouldHaveToString() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        assertNotNull(props.toString());
        assertTrue(props.toString().contains("HeaderHstsProperties"));
    }
}
