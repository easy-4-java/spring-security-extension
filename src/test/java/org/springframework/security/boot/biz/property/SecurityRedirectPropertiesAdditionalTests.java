package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class SecurityRedirectPropertiesAdditionalTests {

    @Test void shouldSetAndGetContextRelative() {
        SecurityRedirectProperties props = new SecurityRedirectProperties();
        props.setContextRelative(true);
        assertTrue(props.isContextRelative());
    }

    @Test void shouldSetAndGetDefaultRedirectUrl() {
        SecurityRedirectProperties props = new SecurityRedirectProperties();
        props.setDefaultRedirectUrl("/home");
        assertEquals("/home", props.getDefaultRedirectUrl());
    }

    @Test void shouldSetAndGetTrustedRedirects() {
        SecurityRedirectProperties props = new SecurityRedirectProperties();
        props.setTrustedRedirects(Arrays.asList("/api/**"));
        assertEquals(1, props.getTrustedRedirects().size());
    }
}
