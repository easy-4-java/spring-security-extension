package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityAuthcPropertiesTests {

    @Test void shouldHaveDefaults() {
        SecurityAuthcProperties props = new SecurityAuthcProperties();
        assertEquals("/login", props.getPathPattern());
        assertEquals("/", props.getRedirectUrl());
        assertEquals("/index", props.getSuccessUrl());
        assertEquals("/error", props.getFailureUrl());
        assertFalse(props.isContinueChainBeforeSuccessfulAuthentication());
        assertFalse(props.isAlwaysUseDefaultTargetUrl());
        assertEquals("/", props.getDefaultTargetUrl());
        assertEquals("target", props.getTargetUrlParameter());
        assertTrue(props.isPostOnly());
        assertFalse(props.isUseForward());
        assertFalse(props.isUseReferer());
    }

    @Test void shouldSettersAndGetters() {
        SecurityAuthcProperties props = new SecurityAuthcProperties();
        props.setPathPattern("/auth");
        assertEquals("/auth", props.getPathPattern());
        props.setRedirectUrl("/home");
        assertEquals("/home", props.getRedirectUrl());
        props.setSuccessUrl("/dashboard");
        assertEquals("/dashboard", props.getSuccessUrl());
        props.setFailureUrl("/err");
        assertEquals("/err", props.getFailureUrl());
        props.setContinueChainBeforeSuccessfulAuthentication(true);
        assertTrue(props.isContinueChainBeforeSuccessfulAuthentication());
        props.setAlwaysUseDefaultTargetUrl(true);
        assertTrue(props.isAlwaysUseDefaultTargetUrl());
        props.setDefaultTargetUrl("/default");
        assertEquals("/default", props.getDefaultTargetUrl());
        props.setTargetUrlParameter("redirect");
        assertEquals("redirect", props.getTargetUrlParameter());
        props.setPostOnly(false);
        assertFalse(props.isPostOnly());
        props.setUseForward(true);
        assertTrue(props.isUseForward());
        props.setUseReferer(true);
        assertTrue(props.isUseReferer());
    }

    @Test void shouldSetTargetUrlParameterToNull() {
        SecurityAuthcProperties props = new SecurityAuthcProperties();
        props.setTargetUrlParameter(null);
        assertNull(props.getTargetUrlParameter());
    }

    @Test void shouldHaveSubProperties() {
        SecurityAuthcProperties props = new SecurityAuthcProperties();
        assertNotNull(props.getHeaders());
        assertNotNull(props.getCors());
        assertNotNull(props.getCsrf());
        assertNotNull(props.getRetry());
        assertNotNull(props.getEntryPoint());
        assertNotNull(props.getRedirect());
    }
}
