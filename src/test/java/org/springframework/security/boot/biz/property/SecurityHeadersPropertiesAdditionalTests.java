package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeadersPropertiesAdditionalTests {

    @Test void shouldHaveAllSubProperties() {
        SecurityHeadersProperties props = new SecurityHeadersProperties();
        assertNotNull(props.getCacheControl());
        assertNotNull(props.getContentSecurityPolicy());
        assertNotNull(props.getContentTypeOptions());
        assertNotNull(props.getFeaturePolicy());
        assertNotNull(props.getFrameOptions());
        assertNotNull(props.getHpkp());
        assertNotNull(props.getHsts());
        assertNotNull(props.getReferrerPolicy());
        assertNotNull(props.getXssProtection());
    }

    @Test void shouldSetAndGetEnabled() {
        SecurityHeadersProperties props = new SecurityHeadersProperties();
        props.setEnabled(false);
        assertFalse(props.isEnabled());
    }
}
