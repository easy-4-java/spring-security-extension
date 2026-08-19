package org.springframework.security.extension.userdetails;

import io.github.easy4j.jwt.JwtPayload;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserProfilePayloadTest {

    @Test
    void shouldCreateInstanceWithDefaults() {
        UserProfilePayload payload = new UserProfilePayload();
        assertNull(payload.getUid());
        assertNull(payload.getUuid());
        assertNull(payload.getUkey());
        assertNull(payload.getUcode());
        assertNull(payload.getRid());
        assertNull(payload.getRkey());
        assertNull(payload.getRcode());
        assertNull(payload.getToken());
        assertFalse(payload.isBound());
        assertFalse(payload.isInitial());
        assertFalse(payload.isVerify());
        assertNotNull(payload.getProfile());
        assertTrue(payload.getProfile().isEmpty());
        assertNotNull(payload.getRoles());
        assertTrue(payload.getRoles().isEmpty());
        assertNotNull(payload.getPerms());
        assertTrue(payload.getPerms().isEmpty());
    }

    @Test
    void shouldSetUid() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUid("100");
        assertEquals("100", p.getUid());
    }

    @Test
    void shouldSetUuid() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUuid("abc-def");
        assertEquals("abc-def", p.getUuid());
    }

    @Test
    void shouldSetUkey() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUkey("EMP001");
        assertEquals("EMP001", p.getUkey());
    }

    @Test
    void shouldSetUcode() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUcode("JOB001");
        assertEquals("JOB001", p.getUcode());
    }

    @Test
    void shouldSetRid() {
        UserProfilePayload p = new UserProfilePayload();
        p.setRid("10");
        assertEquals("10", p.getRid());
    }

    @Test
    void shouldSetRkey() {
        UserProfilePayload p = new UserProfilePayload();
        p.setRkey("admin");
        assertEquals("admin", p.getRkey());
    }

    @Test
    void shouldSetRcode() {
        UserProfilePayload p = new UserProfilePayload();
        p.setRcode("ADMIN_ROLE");
        assertEquals("ADMIN_ROLE", p.getRcode());
    }

    @Test
    void shouldSetToken() {
        UserProfilePayload p = new UserProfilePayload();
        p.setToken("jwt-token");
        assertEquals("jwt-token", p.getToken());
    }

    @Test
    void shouldSetBound() {
        UserProfilePayload p = new UserProfilePayload();
        p.setBound(true);
        assertTrue(p.isBound());
    }

    @Test
    void shouldSetInitial() {
        UserProfilePayload p = new UserProfilePayload();
        p.setInitial(true);
        assertTrue(p.isInitial());
    }

    @Test
    void shouldSetVerify() {
        UserProfilePayload p = new UserProfilePayload();
        p.setVerify(true);
        assertTrue(p.isVerify());
    }

    @Test
    void shouldSetProfile() {
        UserProfilePayload p = new UserProfilePayload();
        Map<String, Object> profile = new HashMap<>();
        profile.put("email", "test@example.com");
        p.setProfile(profile);
        assertEquals("test@example.com", p.getProfile().get("email"));
    }

    @Test
    void shouldSetRoles() {
        UserProfilePayload p = new UserProfilePayload();
        List<JwtPayload.RolePair> roles = new ArrayList<>();
        roles.add(new JwtPayload.RolePair("1", "admin", "Admin"));
        p.setRoles(roles);
        assertEquals(1, p.getRoles().size());
        assertEquals("admin", p.getRoles().get(0).getKey());
    }

    @Test
    void shouldSetPerms() {
        UserProfilePayload p = new UserProfilePayload();
        Set<String> perms = new HashSet<>(Arrays.asList("read", "write"));
        p.setPerms(perms);
        assertEquals(2, p.getPerms().size());
        assertTrue(p.getPerms().contains("read"));
        assertTrue(p.getPerms().contains("write"));
    }

    @Test
    void shouldBeEqualWhenFieldsMatch() {
        UserProfilePayload a = new UserProfilePayload();
        a.setUid("1");
        a.setUkey("k1");

        UserProfilePayload b = new UserProfilePayload();
        b.setUid("1");
        b.setUkey("k1");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenFieldsDiffer() {
        UserProfilePayload a = new UserProfilePayload();
        a.setUid("1");

        UserProfilePayload b = new UserProfilePayload();
        b.setUid("2");

        assertNotEquals(a, b);
    }

    @Test
    void shouldNotBeEqualToNull() {
        UserProfilePayload p = new UserProfilePayload();
        assertNotEquals(null, p);
    }

    @Test
    void shouldNotBeEqualToDifferentType() {
        UserProfilePayload p = new UserProfilePayload();
        assertNotEquals("string", p);
    }

    @Test
    void shouldBeEqualToSelf() {
        UserProfilePayload p = new UserProfilePayload();
        assertEquals(p, p);
    }

    @Test
    void shouldHaveToString() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUid("1");
        String str = p.toString();
        assertNotNull(str);
        assertTrue(str.contains("uid=1"));
    }
}
