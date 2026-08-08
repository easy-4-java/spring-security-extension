package org.springframework.security.boot.biz.userdetails;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SecurityPrincipalAdditionalTests {

    @Test void shouldReturnFalseForAdminWithNullRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(null);
        assertFalse(p.isAdmin());
    }

    @Test void shouldReturnFalseForAdminWithEmptyRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(new ArrayList<>());
        assertFalse(p.isAdmin());
    }

    @Test void shouldReturnTrueForAdminWhenRkeyIsAdmin() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(Arrays.asList("user"));
        p.setRkey("admin");
        assertTrue(p.isAdmin());
    }

    @Test void shouldReturnTrueForAdminWhenRidIsAdmin() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(Arrays.asList("user"));
        p.setRid("admin");
        assertTrue(p.isAdmin());
    }

    @Test void shouldReturnFalseForHasRoleWithBlank() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertFalse(p.hasRole(""));
        assertFalse(p.hasRole(null));
        assertFalse(p.hasRole("  "));
    }

    @Test void shouldReturnFalseForHasRoleWithNullRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(null);
        assertFalse(p.hasRole("admin"));
    }

    @Test void shouldReturnFalseForHasRoleWithEmptyRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(new ArrayList<>());
        assertFalse(p.hasRole("admin"));
    }

    @Test void shouldReturnFalseForHasAnyRoleWithBlank() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertFalse(p.hasAnyRole(""));
        assertFalse(p.hasAnyRole(null));
    }

    @Test void shouldReturnFalseForHasAnyRoleWithNullRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(null);
        assertFalse(p.hasAnyRole("admin"));
    }

    @Test void shouldReturnFalseForHasAnyRoleWithEmptyRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(new ArrayList<>());
        assertFalse(p.hasAnyRole("admin"));
    }

    @Test void shouldConvertToPayloadWithEmptyProfile() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setProfile(new HashMap<>());
        UserProfilePayload payload = p.toPayload();
        assertNotNull(payload.getProfile());
    }

    @Test void shouldConvertToPayloadWithRolesAndPerms() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(Arrays.asList("admin", "user"));
        p.setPerms(new HashSet<>(Arrays.asList("read", "write")));
        UserProfilePayload payload = p.toPayload();
        assertNotNull(payload.getRoles());
        assertNotNull(payload.getPerms());
    }

    @Test void shouldHaveDefaultPerms() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertNotNull(p.getPerms());
    }

    @Test void shouldHaveDefaultProfile() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertNotNull(p.getProfile());
    }

    @Test void shouldHandleNullUidInEquals() {
        SecurityPrincipal p1 = new SecurityPrincipal("user", "pass", "ROLE_USER");
        SecurityPrincipal p2 = new SecurityPrincipal("user", "pass", "ROLE_USER");
        // Both have null uid
        assertEquals(p1, p2);
        assertEquals(0, p1.hashCode());
        assertEquals(0, p2.hashCode());
    }

    @Test void shouldHandleRoleAuthoritiesWithEmptyRoles() {
        Collection<? extends org.springframework.security.core.GrantedAuthority> authorities =
                SecurityPrincipal.roleAuthorities(new ArrayList<>());
        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());
    }
}
