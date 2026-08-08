package org.springframework.security.boot.biz.userdetails;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserProfilePayloadTests {

    @Test void shouldSetAndGetUid() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUid("uid1");
        assertEquals("uid1", p.getUid());
    }

    @Test void shouldSetAndGetUuid() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUuid("uuid1");
        assertEquals("uuid1", p.getUuid());
    }

    @Test void shouldSetAndGetUkey() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUkey("ukey1");
        assertEquals("ukey1", p.getUkey());
    }

    @Test void shouldSetAndGetUcode() {
        UserProfilePayload p = new UserProfilePayload();
        p.setUcode("ucode1");
        assertEquals("ucode1", p.getUcode());
    }

    @Test void shouldSetAndGetRid() {
        UserProfilePayload p = new UserProfilePayload();
        p.setRid("rid1");
        assertEquals("rid1", p.getRid());
    }

    @Test void shouldSetAndGetRkey() {
        UserProfilePayload p = new UserProfilePayload();
        p.setRkey("rkey1");
        assertEquals("rkey1", p.getRkey());
    }

    @Test void shouldSetAndGetRcode() {
        UserProfilePayload p = new UserProfilePayload();
        p.setRcode("rcode1");
        assertEquals("rcode1", p.getRcode());
    }

    @Test void shouldSetAndGetToken() {
        UserProfilePayload p = new UserProfilePayload();
        p.setToken("jwt");
        assertEquals("jwt", p.getToken());
    }

    @Test void shouldSetAndGetBound() {
        UserProfilePayload p = new UserProfilePayload();
        p.setBound(true);
        assertTrue(p.isBound());
    }

    @Test void shouldSetAndGetInitial() {
        UserProfilePayload p = new UserProfilePayload();
        p.setInitial(true);
        assertTrue(p.isInitial());
    }

    @Test void shouldSetAndGetVerify() {
        UserProfilePayload p = new UserProfilePayload();
        p.setVerify(true);
        assertTrue(p.isVerify());
    }

    @Test void shouldSetAndGetProfile() {
        UserProfilePayload p = new UserProfilePayload();
        Map<String, Object> profile = new HashMap<>();
        profile.put("k", "v");
        p.setProfile(profile);
        assertEquals("v", p.getProfile().get("k"));
    }

    @Test void shouldSetAndGetRoles() {
        UserProfilePayload p = new UserProfilePayload();
        List<Object> roles = Arrays.asList("admin");
        p.setRoles(roles);
        assertEquals(roles, p.getRoles());
    }

    @Test void shouldSetAndGetPerms() {
        UserProfilePayload p = new UserProfilePayload();
        Set<String> perms = new HashSet<>(Arrays.asList("read"));
        p.setPerms(perms);
        assertEquals(perms, p.getPerms());
    }
}
