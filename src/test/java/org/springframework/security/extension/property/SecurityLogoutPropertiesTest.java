package org.springframework.security.extension.property;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityLogoutPropertiesTest {

    @Test
    void shouldDefaultPathPatternsToLogout() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        assertEquals("/logout", props.getPathPatterns());
    }

    @Test
    void shouldDefaultInvalidateHttpSessionToTrue() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        assertTrue(props.isInvalidateHttpSession());
    }

    @Test
    void shouldDefaultClearAuthenticationToTrue() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        assertTrue(props.isClearAuthentication());
    }

    @Test
    void shouldDefaultLogoutUrlToNull() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        assertNull(props.getLogoutUrl());
    }

    @Test
    void shouldDefaultLogoutSuccessUrlToNull() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        assertNull(props.getLogoutSuccessUrl());
    }

    @Test
    void shouldSetLogoutUrl() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setLogoutUrl("/signout");
        assertEquals("/signout", props.getLogoutUrl());
    }

    @Test
    void shouldSetPathPatterns() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setPathPatterns("/exit");
        assertEquals("/exit", props.getPathPatterns());
    }

    @Test
    void shouldSetLogoutSuccessUrl() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setLogoutSuccessUrl("/goodbye");
        assertEquals("/goodbye", props.getLogoutSuccessUrl());
    }

    @Test
    void shouldSetInvalidateHttpSession() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setInvalidateHttpSession(false);
        assertFalse(props.isInvalidateHttpSession());
    }

    @Test
    void shouldSetClearAuthentication() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        props.setClearAuthentication(false);
        assertFalse(props.isClearAuthentication());
    }

    @Test
    void shouldHaveToString() {
        SecurityLogoutProperties props = new SecurityLogoutProperties();
        String str = props.toString();
        assertNotNull(str);
        assertTrue(str.contains("SecurityLogoutProperties"));
    }
}
