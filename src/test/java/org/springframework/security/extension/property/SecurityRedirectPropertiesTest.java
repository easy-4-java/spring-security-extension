package org.springframework.security.extension.property;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityRedirectPropertiesTest {

    @Test
    void shouldDefaultContextRelativeToFalse() {
        SecurityRedirectProperties props = new SecurityRedirectProperties();
        assertFalse(props.isContextRelative());
    }

    @Test
    void shouldSetContextRelative() {
        SecurityRedirectProperties props = new SecurityRedirectProperties();
        props.setContextRelative(true);
        assertTrue(props.isContextRelative());
    }

    @Test
    void shouldHaveToString() {
        SecurityRedirectProperties props = new SecurityRedirectProperties();
        String str = props.toString();
        assertNotNull(str);
        assertTrue(str.contains("SecurityRedirectProperties"));
    }
}
