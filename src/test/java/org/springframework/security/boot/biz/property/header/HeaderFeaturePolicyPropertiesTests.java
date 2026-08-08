package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderFeaturePolicyPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderFeaturePolicyProperties props = new HeaderFeaturePolicyProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetPolicyDirectives() {
        HeaderFeaturePolicyProperties props = new HeaderFeaturePolicyProperties();
        props.setPolicyDirectives("geolocation 'self'");
        assertEquals("geolocation 'self'", props.getPolicyDirectives());
    }
}
