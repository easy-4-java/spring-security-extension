package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AbstractAuthenticationTokenTests {

    @Test void shouldCreateUnauthenticatedToken() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("principal");
        assertEquals("principal", token.getPrincipal());
        assertNull(token.getCredentials());
        assertFalse(token.isAuthenticated());
    }

    @Test void shouldCreateAuthenticatedToken() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("principal", "credentials",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(token.isAuthenticated());
        assertEquals("credentials", token.getCredentials());
    }

    @Test void shouldNotAllowSetAuthenticatedTrue() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("principal");
        assertThrows(IllegalArgumentException.class, () -> token.setAuthenticated(true));
    }

    @Test void shouldAllowSetAuthenticatedFalse() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("principal");
        assertDoesNotThrow(() -> token.setAuthenticated(false));
    }

    @Test void shouldEraseCredentials() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("principal", "cred",
                Collections.emptyList());
        token.eraseCredentials();
        assertNull(token.getCredentials());
    }

    @Test void shouldSetAndGetUid() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("p");
        token.setUid("uid1");
        assertEquals("uid1", token.getUid());
    }

    @Test void shouldSetAndGetAppId() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("p");
        token.setAppId("app1");
        assertEquals("app1", token.getAppId());
    }

    @Test void shouldSetAndGetAppChannel() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("p");
        token.setAppChannel("ch1");
        assertEquals("ch1", token.getAppChannel());
    }

    @Test void shouldSetAndGetAppVersion() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("p");
        token.setAppVersion("1.0");
        assertEquals("1.0", token.getAppVersion());
    }

    @Test void shouldSetAndGetSign() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("p");
        token.setSign("sig");
        assertEquals("sig", token.getSign());
    }

    @Test void shouldSetAndGetLongitude() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("p");
        token.setLongitude(121.5);
        assertEquals(121.5, token.getLongitude());
    }

    @Test void shouldSetAndGetLatitude() {
        AbstractAuthenticationToken token = new AbstractAuthenticationToken("p");
        token.setLatitude(31.2);
        assertEquals(31.2, token.getLatitude());
    }
}
