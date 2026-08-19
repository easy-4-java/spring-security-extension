package org.springframework.security.extension.property;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityEntryPointPropertiesTest {

    @Test
    void shouldDefaultForceHttpsToFalse() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        assertFalse(props.isForceHttps());
    }

    @Test
    void shouldDefaultUseForwardToFalse() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        assertFalse(props.isUseForward());
    }

    @Test
    void shouldSetForceHttps() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        props.setForceHttps(true);
        assertTrue(props.isForceHttps());
    }

    @Test
    void shouldSetUseForward() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        props.setUseForward(true);
        assertTrue(props.isUseForward());
    }

    @Test
    void shouldHaveToString() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        String str = props.toString();
        assertNotNull(str);
        assertTrue(str.contains("SecurityEntryPointProperties"));
    }
}
