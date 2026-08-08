package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.boot.biz.property.SecurityAuthcProperties;
import org.springframework.security.boot.biz.property.SecuritySessionMgtProperties;
import org.springframework.security.boot.biz.property.SessionFixationPolicy;

import static org.junit.jupiter.api.Assertions.*;

class WebSecurityUtilsTests {

    @Test void shouldCreateCsrfTokenRepositoryWithChangeSessionId() {
        SecuritySessionMgtProperties props = new SecuritySessionMgtProperties();
        props.setFixationPolicy(SessionFixationPolicy.CHANGE_SESSION_ID);
        assertNotNull(WebSecurityUtils.csrfTokenRepository(props));
    }

    @Test void shouldCreateCsrfTokenRepositoryWithDefault() {
        SecuritySessionMgtProperties props = new SecuritySessionMgtProperties();
        assertNotNull(WebSecurityUtils.csrfTokenRepository(props));
    }

    @Test void shouldCreateAuthEntryPoint() {
        SecurityAuthcProperties authcProps = new SecurityAuthcProperties();
        SecuritySessionMgtProperties sessionProps = new SecuritySessionMgtProperties();
        assertNotNull(WebSecurityUtils.authenticationEntryPoint(authcProps, sessionProps, java.util.Collections.emptyList()));
    }

    @Test void shouldCreateFailureHandler() {
        SecurityAuthcProperties authcProps = new SecurityAuthcProperties();
        SecuritySessionMgtProperties sessionProps = new SecuritySessionMgtProperties();
        assertNotNull(WebSecurityUtils.authenticationFailureHandler(authcProps, sessionProps, null, java.util.Collections.emptyList()));
    }

    @Test void shouldCreateSuccessHandler() {
        SecurityAuthcProperties authcProps = new SecurityAuthcProperties();
        SecuritySessionMgtProperties sessionProps = new SecuritySessionMgtProperties();
        assertNotNull(WebSecurityUtils.authenticationSuccessHandler(authcProps, sessionProps, null, java.util.Collections.emptyList()));
    }

    @Test void shouldCreateFailureCounter() {
        SecurityAuthcProperties authcProps = new SecurityAuthcProperties();
        assertNotNull(WebSecurityUtils.authenticatingFailureCounter(authcProps));
    }

    @Test void shouldCreateFailureForwardHandler() {
        assertNotNull(WebSecurityUtils.authenticationFailureForwardHandler("/error"));
    }

    @Test void shouldCreateFailureSimpleUrlHandler() {
        SecurityAuthcProperties authcProps = new SecurityAuthcProperties();
        SecuritySessionMgtProperties sessionProps = new SecuritySessionMgtProperties();
        assertNotNull(WebSecurityUtils.authenticationFailureSimpleUrlHandler(authcProps, sessionProps));
    }

    @Test void shouldCreateRedirectStrategy() {
        SecurityAuthcProperties authcProps = new SecurityAuthcProperties();
        assertNotNull(WebSecurityUtils.redirectStrategy(authcProps));
    }

    @Test void shouldCreateRequestCache() {
        SecurityAuthcProperties authcProps = new SecurityAuthcProperties();
        SecuritySessionMgtProperties sessionProps = new SecuritySessionMgtProperties();
        assertNotNull(WebSecurityUtils.requestCache(authcProps, sessionProps));
    }

    @Test void shouldCreateLogoutHandler() {
        assertNotNull(WebSecurityUtils.logoutHandler(java.util.Collections.singletonList((req, res, auth) -> {})));
    }

    @Test void shouldCreateLogoutSuccessHandler() {
        assertNotNull(WebSecurityUtils.logoutSuccessForwardHandler("/logout"));
    }
}
