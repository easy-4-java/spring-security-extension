package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityEntryPointPropertiesAdditionalTests {

    @Test void shouldSetAndGetForceHttps() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        props.setForceHttps(true);
        assertTrue(props.isForceHttps());
    }

    @Test void shouldSetAndGetUseForward() {
        SecurityEntryPointProperties props = new SecurityEntryPointProperties();
        props.setUseForward(true);
        assertTrue(props.isUseForward());
    }
}
