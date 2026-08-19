package org.springframework.security.extension.property.header;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HeaderHpkpPropertiesTest {

    @Test
    void shouldDefaultEnabledToFalse() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertFalse(props.isEnabled());
    }

    @Test
    void shouldDefaultIncludeSubDomainsToFalse() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertFalse(props.isIncludeSubDomains());
    }

    @Test
    void shouldDefaultMaxAgeInSecondsToZero() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertEquals(0L, props.getMaxAgeInSeconds());
    }

    @Test
    void shouldDefaultReportOnlyToTrue() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertTrue(props.isReportOnly());
    }

    @Test
    void shouldDefaultReportUriToNull() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertNull(props.getReportUri());
    }

    @Test
    void shouldDefaultSha256PinsToEmptyArray() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertNotNull(props.getSha256Pins());
        assertEquals(0, props.getSha256Pins().length);
    }

    @Test
    void shouldDefaultPinsToEmptyMap() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertNotNull(props.getPins());
        assertTrue(props.getPins().isEmpty());
    }

    @Test
    void shouldSetEnabled() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        props.setEnabled(true);
        assertTrue(props.isEnabled());
    }

    @Test
    void shouldSetIncludeSubDomains() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        props.setIncludeSubDomains(true);
        assertTrue(props.isIncludeSubDomains());
    }

    @Test
    void shouldSetMaxAgeInSeconds() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        props.setMaxAgeInSeconds(5184000L);
        assertEquals(5184000L, props.getMaxAgeInSeconds());
    }

    @Test
    void shouldSetReportOnly() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        props.setReportOnly(false);
        assertFalse(props.isReportOnly());
    }

    @Test
    void shouldSetReportUri() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        props.setReportUri("https://example.com/report");
        assertEquals("https://example.com/report", props.getReportUri());
    }

    @Test
    void shouldSetSha256Pins() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        String[] pins = {"pin1", "pin2"};
        props.setSha256Pins(pins);
        assertArrayEquals(pins, props.getSha256Pins());
    }

    @Test
    void shouldSetPins() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        Map<String, String> pinMap = new HashMap<>();
        pinMap.put("key", "value");
        props.setPins(pinMap);
        assertEquals("value", props.getPins().get("key"));
    }

    @Test
    void shouldHaveToString() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertNotNull(props.toString());
        assertTrue(props.toString().contains("HeaderHpkpProperties"));
    }
}
