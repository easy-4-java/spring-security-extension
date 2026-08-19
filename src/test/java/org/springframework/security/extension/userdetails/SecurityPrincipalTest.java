package org.springframework.security.extension.userdetails;

import io.github.easy4j.jwt.JwtPayload.RolePair;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SecurityPrincipalTest {

    // --- constructors ---

    @Test
    void shouldCreateWithUsernamePasswordAndRoles() {
        SecurityPrincipal p = new SecurityPrincipal("alice", "encoded", "ROLE_USER", "ROLE_ADMIN");
        assertEquals("alice", p.getUsername());
        assertEquals("encoded", p.getPassword());
        assertTrue(p.isEnabled());
        assertEquals(2, p.getAuthorities().size());
    }

    @Test
    void shouldCreateWithEmptyRoles() {
        SecurityPrincipal p = new SecurityPrincipal("bob", "pw");
        assertEquals("bob", p.getUsername());
        assertEquals(0, p.getAuthorities().size());
    }

    @Test
    void shouldCreateWithAuthoritiesCollection() {
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        SecurityPrincipal p = new SecurityPrincipal("charlie", "pw", authorities);
        assertEquals(1, p.getAuthorities().size());
        assertTrue(p.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void shouldCreateWithAccountStatusFlags() {
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        SecurityPrincipal p = new SecurityPrincipal("dave", "pw", true, true, true, false, authorities);
        assertTrue(p.isEnabled());
        assertTrue(p.isAccountNonExpired());
        assertTrue(p.isCredentialsNonExpired());
        assertFalse(p.isAccountNonLocked());
    }

    // --- roleAuthorities ---

    @Test
    void shouldConvertRoleNamesToAuthorities() {
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        Collection<? extends GrantedAuthority> authorities = SecurityPrincipal.roleAuthorities(roles);
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void shouldThrowWhenRolesIsNull() {
        assertThrows(InsufficientAuthenticationException.class, () -> SecurityPrincipal.roleAuthorities(null));
    }

    @Test
    void shouldReturnEmptyAuthoritiesForEmptyRolesList() {
        Collection<? extends GrantedAuthority> authorities = SecurityPrincipal.roleAuthorities(Collections.emptyList());
        assertTrue(authorities.isEmpty());
    }

    // --- getters/setters ---

    @Test
    void shouldSetAndGetUid() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setUid("100");
        assertEquals("100", p.getUid());
    }

    @Test
    void shouldSetAndGetUuid() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setUuid("uuid-1");
        assertEquals("uuid-1", p.getUuid());
    }

    @Test
    void shouldSetAndGetUkey() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setUkey("EMP001");
        assertEquals("EMP001", p.getUkey());
    }

    @Test
    void shouldSetAndGetUcode() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setUcode("JOB001");
        assertEquals("JOB001", p.getUcode());
    }

    @Test
    void shouldSetAndGetRid() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRid("10");
        assertEquals("10", p.getRid());
    }

    @Test
    void shouldSetAndGetRkey() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRkey("admin");
        assertEquals("admin", p.getRkey());
    }

    @Test
    void shouldSetAndGetRcode() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRcode("ADMIN_ROLE");
        assertEquals("ADMIN_ROLE", p.getRcode());
    }

    @Test
    void shouldSetAndGetBound() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertFalse(p.isBound());
        p.setBound(true);
        assertTrue(p.isBound());
    }

    @Test
    void shouldSetAndGetInitial() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertFalse(p.isInitial());
        p.setInitial(true);
        assertTrue(p.isInitial());
    }

    @Test
    void shouldSetAndGetVerify() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertFalse(p.isVerify());
        p.setVerify(true);
        assertTrue(p.isVerify());
    }

    @Test
    void shouldSetAndGetSign() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertNull(p.getSign());
        p.setSign("signature");
        assertEquals("signature", p.getSign());
    }

    @Test
    void shouldSetAndGetLongitude() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertEquals(0.0, p.getLongitude());
        p.setLongitude(116.4);
        assertEquals(116.4, p.getLongitude());
    }

    @Test
    void shouldSetAndGetLatitude() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertEquals(0.0, p.getLatitude());
        p.setLatitude(39.9);
        assertEquals(39.9, p.getLatitude());
    }

    @Test
    void shouldSetAndGetRoles() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertNull(p.getRoles());
        List<RolePair> roles = List.of(new RolePair("1", "admin", "Admin"));
        p.setRoles(roles);
        assertEquals(1, p.getRoles().size());
    }

    @Test
    void shouldSetAndGetPerms() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertNotNull(p.getPerms());
        Set<String> perms = new HashSet<>(Arrays.asList("read", "write"));
        p.setPerms(perms);
        assertEquals(2, p.getPerms().size());
    }

    @Test
    void shouldSetAndGetProfile() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertNotNull(p.getProfile());
        Map<String, Object> profile = Map.of("key", "val");
        p.setProfile(profile);
        assertEquals("val", p.getProfile().get("key"));
    }

    // --- isAdmin ---

    @Test
    void shouldReturnFalseForAdminWhenRolesIsNull() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(null);
        assertFalse(p.isAdmin());
    }

    @Test
    void shouldReturnFalseForAdminWhenRolesIsEmpty() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(new ArrayList<>());
        assertFalse(p.isAdmin());
    }

    @Test
    void shouldBeAdminWhenRkeyIsAdmin() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "user", "User")));
        p.setRkey("admin");
        assertTrue(p.isAdmin());
    }

    @Test
    void shouldBeAdminWhenRkeyIsAdminCaseInsensitive() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "user", "User")));
        p.setRkey("ADMIN");
        assertTrue(p.isAdmin());
    }

    @Test
    void shouldBeAdminWhenRidIsAdmin() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "user", "User")));
        p.setRid("admin");
        assertTrue(p.isAdmin());
    }

    @Test
    void shouldNotBeAdminWhenRkeyAndRidAreNotAdmin() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "user", "User")));
        p.setRkey("user");
        p.setRid("2");
        assertFalse(p.isAdmin());
    }

    // --- hasRole ---

    @Test
    void shouldReturnFalseForHasRoleWhenRoleIsBlank() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertFalse(p.hasRole(""));
        assertFalse(p.hasRole(null));
        assertFalse(p.hasRole("  "));
    }

    @Test
    void shouldReturnFalseForHasRoleWhenRolesListIsNull() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(null);
        assertFalse(p.hasRole("admin"));
    }

    @Test
    void shouldReturnFalseForHasRoleWhenRolesListIsEmpty() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(new ArrayList<>());
        assertFalse(p.hasRole("admin"));
    }

    @Test
    void shouldReturnTrueForHasRoleWhenRoleMatches() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "admin", "Admin")));
        assertTrue(p.hasRole("admin"));
    }

    @Test
    void shouldReturnTrueForHasRoleCaseInsensitive() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "admin", "Admin")));
        assertTrue(p.hasRole("ADMIN"));
        assertTrue(p.hasRole("Admin"));
    }

    @Test
    void shouldReturnFalseForHasRoleWhenRoleDoesNotMatch() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "user", "User")));
        assertFalse(p.hasRole("admin"));
    }

    // --- hasAnyRole ---

    @Test
    void shouldReturnFalseForHasAnyRoleWhenRolesArgIsBlank() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertFalse(p.hasAnyRole(""));
        assertFalse(p.hasAnyRole("  "));
    }

    @Test
    void shouldReturnFalseForHasAnyRoleWhenPrincipalRolesIsNull() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(null);
        assertFalse(p.hasAnyRole("admin"));
    }

    @Test
    void shouldReturnFalseForHasAnyRoleWhenPrincipalRolesIsEmpty() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(new ArrayList<>());
        assertFalse(p.hasAnyRole("admin"));
    }

    @Test
    void shouldReturnFalseForHasAnyRoleDueToTypeMismatch() {
        // CollectionUtils.containsAny compares RolePair with String, which never matches
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(List.of(new RolePair("1", "admin", "Admin")));
        assertFalse(p.hasAnyRole("admin"));
    }

    // --- equals / hashCode ---

    @Test
    void shouldBeEqualWhenUidMatches() {
        SecurityPrincipal a = new SecurityPrincipal("u1", "p", "R");
        a.setUid("42");
        SecurityPrincipal b = new SecurityPrincipal("u2", "p", "R");
        b.setUid("42");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenUidDiffers() {
        SecurityPrincipal a = new SecurityPrincipal("u", "p", "R");
        a.setUid("1");
        SecurityPrincipal b = new SecurityPrincipal("u", "p", "R");
        b.setUid("2");
        assertNotEquals(a, b);
    }

    @Test
    void shouldNotBeEqualToNull() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertNotEquals(null, p);
    }

    @Test
    void shouldNotBeEqualToDifferentType() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertNotEquals("string", p);
    }

    @Test
    void shouldBeEqualToSelf() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertEquals(p, p);
    }

    @Test
    void shouldHaveConsistentHashCode() {
        SecurityPrincipal a = new SecurityPrincipal("u", "p", "R");
        a.setUid("42");
        SecurityPrincipal b = new SecurityPrincipal("u", "p", "R");
        b.setUid("42");
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void shouldHaveZeroHashCodeWhenUidIsNull() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        assertEquals(0, p.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenOneUidIsNull() {
        SecurityPrincipal a = new SecurityPrincipal("u", "p", "R");
        a.setUid("1");
        SecurityPrincipal b = new SecurityPrincipal("u", "p", "R");
        assertNotEquals(a, b);
    }

    // --- toString ---

    @Test
    void shouldHaveMeaningfulToString() {
        SecurityPrincipal p = new SecurityPrincipal("alice", "secret", "R");
        p.setUid("42");
        String str = p.toString();
        assertNotNull(str);
        assertTrue(str.contains("userid=42"));
        assertTrue(str.contains("username='alice'"));
    }

    // --- toPayload ---

    @Test
    void shouldConvertToPayload() {
        SecurityPrincipal p = new SecurityPrincipal("alice", "pw", "ROLE_USER");
        p.setUid("42");
        p.setUuid("uuid-42");
        p.setUkey("ukey-42");
        p.setUcode("ucode-42");
        p.setRid("10");
        p.setRkey("admin");
        p.setRcode("ADMIN");
        p.setBound(true);
        p.setInitial(true);
        p.setVerify(false);

        Map<String, Object> profile = new HashMap<>();
        profile.put("email", "alice@example.com");
        p.setProfile(profile);

        Set<String> perms = new HashSet<>(Arrays.asList("read", "write"));
        p.setPerms(perms);

        List<RolePair> roles = List.of(new RolePair("1", "admin", "Admin"));
        p.setRoles(roles);

        UserProfilePayload payload = p.toPayload();

        assertNotNull(payload);
        assertEquals("42", payload.getUid());
        assertEquals("uuid-42", payload.getUuid());
        assertEquals("ukey-42", payload.getUkey());
        assertEquals("ucode-42", payload.getUcode());
        assertEquals("10", payload.getRid());
        assertEquals("admin", payload.getRkey());
        assertEquals("ADMIN", payload.getRcode());
        assertTrue(payload.isBound());
        assertTrue(payload.isInitial());
        assertFalse(payload.isVerify());
        assertEquals("alice@example.com", payload.getProfile().get("email"));
        assertEquals(2, payload.getPerms().size());
        assertTrue(payload.getPerms().contains("read"));
        assertTrue(payload.getPerms().contains("write"));
        assertEquals(1, payload.getRoles().size());
    }

    @Test
    void shouldDefensivelyCopyPermsInToPayload() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        Set<String> perms = new HashSet<>(Arrays.asList("read"));
        p.setPerms(perms);

        UserProfilePayload payload = p.toPayload();
        payload.getPerms().add("write");

        // Original should not be affected
        assertEquals(1, p.getPerms().size());
    }

    @Test
    void shouldUseEmptyMapWhenProfileIsEmpty() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setProfile(new HashMap<>());

        UserProfilePayload payload = p.toPayload();
        assertNotNull(payload.getProfile());
        assertTrue(payload.getProfile().isEmpty());
    }

    @Test
    void shouldShareProfileMapReferenceWhenProfileIsNotEmpty() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        Map<String, Object> profile = new HashMap<>();
        profile.put("key", "value");
        p.setProfile(profile);

        UserProfilePayload payload = p.toPayload();
        // When profile is not empty, the same reference is shared
        assertSame(p.getProfile(), payload.getProfile());
    }

    @Test
    void shouldHandleNullRolesInToPayload() {
        SecurityPrincipal p = new SecurityPrincipal("u", "p", "R");
        p.setRoles(null);

        UserProfilePayload payload = p.toPayload();
        assertNull(payload.getRoles());
    }
}
