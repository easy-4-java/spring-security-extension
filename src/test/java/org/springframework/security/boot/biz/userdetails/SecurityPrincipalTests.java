package org.springframework.security.boot.biz.userdetails;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SecurityPrincipalTests {

    @Test void shouldCreateWithRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER", "ROLE_ADMIN");
        assertEquals("user", p.getUsername());
        assertEquals("pass", p.getPassword());
        assertTrue(p.isEnabled());
    }

    @Test void shouldCreateWithAuthorities() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass",
                Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER")));
        assertEquals("user", p.getUsername());
    }

    @Test void shouldCreateWithFullConstructor() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", true, true, true, true,
                Collections.emptyList());
        assertTrue(p.isEnabled());
        assertTrue(p.isAccountNonExpired());
        assertTrue(p.isCredentialsNonExpired());
        assertTrue(p.isAccountNonLocked());
    }

    @Test void shouldSetAndGetUid() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setUid("uid1");
        assertEquals("uid1", p.getUid());
    }

    @Test void shouldSetAndGetUuid() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setUuid("uuid1");
        assertEquals("uuid1", p.getUuid());
    }

    @Test void shouldSetAndGetUkey() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setUkey("ukey1");
        assertEquals("ukey1", p.getUkey());
    }

    @Test void shouldSetAndGetUcode() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setUcode("ucode1");
        assertEquals("ucode1", p.getUcode());
    }

    @Test void shouldSetAndGetRid() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRid("rid1");
        assertEquals("rid1", p.getRid());
    }

    @Test void shouldSetAndGetRkey() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRkey("rkey1");
        assertEquals("rkey1", p.getRkey());
    }

    @Test void shouldSetAndGetRcode() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRcode("rcode1");
        assertEquals("rcode1", p.getRcode());
    }

    @Test void shouldSetAndGetBound() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setBound(true);
        assertTrue(p.isBound());
    }

    @Test void shouldSetAndGetInitial() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setInitial(true);
        assertTrue(p.isInitial());
    }

    @Test void shouldSetAndGetVerify() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setVerify(true);
        assertTrue(p.isVerify());
    }

    @Test void shouldSetAndGetSign() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setSign("sig");
        assertEquals("sig", p.getSign());
    }

    @Test void shouldSetAndGetAuthType() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setAuthType("jwt");
        assertEquals("jwt", p.getAuthType());
    }

    @Test void shouldSetAndGetLongitudeLatitude() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setLongitude(121.5);
        p.setLatitude(31.2);
        assertEquals(121.5, p.getLongitude());
        assertEquals(31.2, p.getLatitude());
    }

    @Test void shouldSetAndGetRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        List<Object> roles = Arrays.asList("admin", "user");
        p.setRoles(roles);
        assertEquals(roles, p.getRoles());
    }

    @Test void shouldSetAndGetPerms() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Set<String> perms = new HashSet<>(Arrays.asList("read", "write"));
        p.setPerms(perms);
        assertEquals(perms, p.getPerms());
    }

    @Test void shouldSetAndGetProfile() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("key", "value");
        p.setProfile(profile);
        assertEquals("value", p.getProfile().get("key"));
    }

    @Test void shouldReturnFalseForAdminWhenNoRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(null);
        assertFalse(p.isAdmin());
    }

    @Test void shouldReturnFalseForAdminWhenRolesEmpty() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(new ArrayList<>());
        assertFalse(p.isAdmin());
    }

    @Test void shouldReturnFalseForHasRoleWithBlank() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertFalse(p.hasRole(""));
    }

    @Test void shouldReturnFalseForHasRoleWithNullRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(null);
        assertFalse(p.hasRole("admin"));
    }

    @Test void shouldReturnFalseForHasAnyRoleWithBlank() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertFalse(p.hasAnyRole(""));
    }

    @Test void shouldReturnFalseForHasAnyRoleWithNullRoles() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setRoles(null);
        assertFalse(p.hasAnyRole("admin"));
    }

    @Test void shouldBeEqualByUid() {
        SecurityPrincipal p1 = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p1.setUid("uid1");
        SecurityPrincipal p2 = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p2.setUid("uid1");
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test void shouldNotBeEqualWithDifferentUid() {
        SecurityPrincipal p1 = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p1.setUid("uid1");
        SecurityPrincipal p2 = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p2.setUid("uid2");
        assertNotEquals(p1, p2);
    }

    @Test void shouldNotEqualNull() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertNotEquals(null, p);
    }

    @Test void shouldNotEqualDifferentType() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertNotEquals("string", p);
    }

    @Test void shouldBeEqualToSelf() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertEquals(p, p);
    }

    @Test void shouldHandleNullUidInHashCode() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        assertEquals(0, p.hashCode());
    }

    @Test void shouldConvertToString() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setUid("uid1");
        String str = p.toString();
        assertTrue(str.contains("uid1"));
        assertTrue(str.contains("user"));
    }

    @Test void shouldConvertToPayload() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setUid("uid1");
        p.setUuid("uuid1");
        p.setUkey("ukey1");
        p.setRid("rid1");
        p.setRkey("rkey1");
        UserProfilePayload payload = p.toPayload();
        assertEquals("uid1", payload.getUid());
        assertEquals("uuid1", payload.getUuid());
    }

    @Test void shouldConvertToPayloadWithNullProfile() {
        SecurityPrincipal p = new SecurityPrincipal("user", "pass", "ROLE_USER");
        p.setProfile(null);
        UserProfilePayload payload = p.toPayload();
        assertNotNull(payload.getProfile());
    }

    @Test void shouldRejectNullRoles() {
        assertThrows(org.springframework.security.authentication.InsufficientAuthenticationException.class,
                () -> SecurityPrincipal.roleAuthorities(null));
    }
}
