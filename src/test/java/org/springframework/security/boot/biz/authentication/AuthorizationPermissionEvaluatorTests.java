package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationPermissionEvaluatorTests {

    private final AuthorizationPermissionEvaluator evaluator = new AuthorizationPermissionEvaluator();

    @Test void shouldReturnTrueForWildcardPermission() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(evaluator.hasPermission(auth, (Object) null, "*"));
    }

    @Test void shouldReturnTrueWhenAuthorityMatches() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass",
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        assertTrue(evaluator.hasPermission(auth, (Object) null, "ADMIN"));
    }

    @Test void shouldReturnFalseWhenAuthorityDoesNotMatch() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        assertFalse(evaluator.hasPermission(auth, (Object) null, "ADMIN"));
    }

    @Test void shouldWorkWithSerializableOverload() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass",
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        assertTrue(evaluator.hasPermission(auth, "1", "type", "ADMIN"));
        assertFalse(evaluator.hasPermission(auth, "1", "type", "OTHER"));
        assertTrue(evaluator.hasPermission(auth, "1", "type", "*"));
    }
}
