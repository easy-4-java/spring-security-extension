package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

class SubjectUtilsTests {

    @Test void shouldGetSecurityContext() {
        assertNotNull(SubjectUtils.getSecurityContext());
    }

    @Test void shouldGetAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("user", "pass"));
        assertNotNull(SubjectUtils.getAuthentication());
        SecurityContextHolder.clearContext();
    }

    @Test void shouldReturnNullPrincipalWhenNoAuth() {
        SecurityContextHolder.clearContext();
        assertNull(SubjectUtils.getPrincipal());
    }

    @Test void shouldCheckIsAssignableFrom() {
        assertTrue(SubjectUtils.isAssignableFrom(String.class, Object.class));
        assertFalse(SubjectUtils.isAssignableFrom(Object.class, String.class));
    }

    @Test void shouldReturnFalseForNullTarget() {
        assertFalse(SubjectUtils.isAssignableFrom(null, String.class));
    }

    @Test void shouldReturnFalseForNullClasses() {
        assertFalse(SubjectUtils.isAssignableFrom(String.class, (Class<?>[]) null));
    }

    @Test void shouldHandleIsAuthenticatedWithNull() {
        assertFalse(SubjectUtils.isAuthenticated(null));
    }

    @Test void shouldHandleIsAuthenticatedWithValidAuth() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("u", "p",
                java.util.Collections.emptyList());
        assertTrue(SubjectUtils.isAuthenticated(auth));
    }

    @Test void shouldGetPrincipalWithClass() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("user", "pass"));
        String principal = SubjectUtils.getPrincipal(String.class);
        assertEquals("user", principal);
        SecurityContextHolder.clearContext();
    }

    @Test void shouldReturnNullWhenPrincipalTypeMismatch() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("user", "pass"));
        Integer principal = SubjectUtils.getPrincipal(Integer.class);
        assertNull(principal);
        SecurityContextHolder.clearContext();
    }

    @Test void shouldGetPrincipalFromAuthentication() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("user", "pass");
        String principal = SubjectUtils.getPrincipal(auth, String.class);
        assertEquals("user", principal);
    }

    @Test void shouldReturnNullWhenPrincipalTypeMismatchFromAuth() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("user", "pass");
        Integer principal = SubjectUtils.getPrincipal(auth, Integer.class);
        assertNull(principal);
    }
}
