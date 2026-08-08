package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityRememberMePropertiesAdditionalTests {

    @Test void shouldCreateInstance() {
        SecurityRememberMeProperties props = new SecurityRememberMeProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetContextRelative() {
        SecurityRememberMeProperties props = new SecurityRememberMeProperties();
        props.setContextRelative(true);
        assertTrue(props.isContextRelative());
    }
}
