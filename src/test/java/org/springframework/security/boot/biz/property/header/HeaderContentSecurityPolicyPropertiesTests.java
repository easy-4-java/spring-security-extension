package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderContentSecurityPolicyPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderContentSecurityPolicyProperties props = new HeaderContentSecurityPolicyProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetPolicyDirectives() {
        HeaderContentSecurityPolicyProperties props = new HeaderContentSecurityPolicyProperties();
        props.setPolicyDirectives("default-src 'self'");
        assertEquals("default-src 'self'", props.getPolicyDirectives());
    }
}
