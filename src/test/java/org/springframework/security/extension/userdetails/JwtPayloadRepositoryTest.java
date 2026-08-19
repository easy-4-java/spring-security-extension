package org.springframework.security.extension.userdetails;

import io.github.easy4j.jwt.JwtPayload;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtPayloadRepositoryTest {

    private final JwtPayloadRepository repository = new JwtPayloadRepository() {};

    // --- issueJwt(AbstractAuthenticationToken) ---

    @Test
    void shouldReturnEmptyStringWhenPrincipalIsNotSecurityPrincipal() {
        TestingAuthenticationToken token = new TestingAuthenticationToken("user", "pass");
        assertEquals("", repository.issueJwt(token));
    }

    @Test
    void shouldDelegateToIssueJwtPrincipalWhenPrincipalIsSecurityPrincipal() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        principal.setUid("42");
        principal.setProfile(new HashMap<>());
        TestingAuthenticationToken token = new TestingAuthenticationToken(principal, "pass");

        String jwt = repository.issueJwt(token);
        // Default returns "" since issueJwt(String, Map) returns ""
        assertEquals("", jwt);
    }

    // --- issueJwt(SecurityPrincipal) ---

    @Test
    void shouldDelegateToIssueJwtUidAndProfileFromPrincipal() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        principal.setUid("99");
        Map<String, Object> profile = new HashMap<>();
        profile.put("key", "value");
        principal.setProfile(profile);

        assertEquals("", repository.issueJwt(principal));
    }

    // --- issueJwt(String, Map) ---

    @Test
    void shouldReturnEmptyStringByDefault() {
        Map<String, Object> profile = Collections.singletonMap("k", "v");
        assertEquals("", repository.issueJwt("uid", profile));
    }

    // --- verify(AbstractAuthenticationToken, boolean) ---

    @Test
    void shouldReturnFalseForVerifyWithAuthenticationToken() throws AuthenticationException {
        TestingAuthenticationToken token = new TestingAuthenticationToken("user", "pass");
        assertFalse(repository.verify(token, true));
        assertFalse(repository.verify(token, false));
    }

    // --- verify(String, boolean) ---

    @Test
    void shouldReturnFalseForVerifyWithStringToken() throws AuthenticationException {
        assertFalse(repository.verify("some-jwt", true));
        assertFalse(repository.verify("some-jwt", false));
    }

    // --- getPayload(AbstractAuthenticationToken, boolean) ---

    @Test
    void shouldReturnNullForGetPayloadWithAuthenticationToken() {
        TestingAuthenticationToken token = new TestingAuthenticationToken("user", "pass");
        assertNull(repository.getPayload(token, true));
        assertNull(repository.getPayload(token, false));
    }

    // --- getPayload(String, boolean) ---

    @Test
    void shouldReturnNullForGetPayloadWithString() {
        assertNull(repository.getPayload("some-jwt", true));
        assertNull(repository.getPayload("some-jwt", false));
    }

    // --- getProfilePayload ---

    @Test
    void shouldBuildProfilePayloadFromSecurityPrincipal() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        principal.setUid("42");
        principal.setUuid("uuid-42");
        principal.setUkey("ukey-42");
        principal.setUcode("ucode-42");
        principal.setRid("1");
        principal.setRkey("admin");
        principal.setRcode("ADMIN");
        principal.setBound(true);
        principal.setInitial(true);
        principal.setVerify(false);

        Map<String, Object> profile = new HashMap<>();
        profile.put("email", "test@example.com");
        principal.setProfile(profile);

        TestingAuthenticationToken token = new TestingAuthenticationToken(principal, "pass");

        UserProfilePayload payload = repository.getProfilePayload(token, true);

        assertNotNull(payload);
        assertEquals("42", payload.getUid());
        assertEquals("uuid-42", payload.getUuid());
        assertEquals("ukey-42", payload.getUkey());
        assertEquals("ucode-42", payload.getUcode());
        assertEquals("1", payload.getRid());
        assertEquals("admin", payload.getRkey());
        assertEquals("ADMIN", payload.getRcode());
        assertTrue(payload.isBound());
        assertTrue(payload.isInitial());
        assertFalse(payload.isVerify());
        assertEquals("test@example.com", payload.getProfile().get("email"));
        assertEquals("", payload.getToken()); // default issueJwt returns ""
    }

    @Test
    void shouldHandlePrincipalWithEmptyProfile() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        principal.setUid("1");
        principal.setProfile(new HashMap<>());

        TestingAuthenticationToken token = new TestingAuthenticationToken(principal, "pass");
        UserProfilePayload payload = repository.getProfilePayload(token, false);

        assertNotNull(payload);
        assertNotNull(payload.getProfile());
        assertTrue(payload.getProfile().isEmpty());
    }
}
