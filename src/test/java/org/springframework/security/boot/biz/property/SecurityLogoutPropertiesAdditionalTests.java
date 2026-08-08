package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityLogoutPropertiesAdditionalTests {

    @Test void shouldSetAndGetLogoutUrl() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setLogoutUrl("/signout");
        assertEquals("/signout", props.getLogoutUrl());
    }

    @Test void shouldSetAndGetLogoutSuccessUrl() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setLogoutSuccessUrl("/goodbye");
        assertEquals("/goodbye", props.getLogoutSuccessUrl());
    }

    @Test void shouldSetAndGetPathPatterns() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setPathPatterns("/custom-logout");
        assertEquals("/custom-logout", props.getPathPatterns());
    }

    @Test void shouldSetAndGetInvalidateHttpSession() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setInvalidateHttpSession(false);
        assertFalse(props.isInvalidateHttpSession());
    }

    @Test void shouldSetAndGetClearAuthentication() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setClearAuthentication(false);
        assertFalse(props.isClearAuthentication());
    }
}
