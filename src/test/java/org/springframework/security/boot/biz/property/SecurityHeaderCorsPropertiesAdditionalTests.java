package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeaderCorsPropertiesAdditionalTests {

    @Test void shouldCreateInstance() {
        SecurityHeaderCorsProperties props = new SecurityHeaderCorsProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetEnabled() {
        SecurityHeaderCorsProperties props = new SecurityHeaderCorsProperties();
        props.setEnabled(true);
        assertTrue(props.isEnabled());
    }

    @Test void shouldSetAndGetAlwaysUseFullPath() {
        SecurityHeaderCorsProperties props = new SecurityHeaderCorsProperties();
        props.setAlwaysUseFullPath(true);
        assertTrue(props.isAlwaysUseFullPath());
    }

    @Test void shouldSetAndGetUrlDecode() {
        SecurityHeaderCorsProperties props = new SecurityHeaderCorsProperties();
        props.setUrlDecode(true);
        assertTrue(props.isUrlDecode());
    }

    @Test void shouldSetAndGetRemoveSemicolonContent() {
        SecurityHeaderCorsProperties props = new SecurityHeaderCorsProperties();
        props.setRemoveSemicolonContent(true);
        assertTrue(props.isRemoveSemicolonContent());
    }
}
