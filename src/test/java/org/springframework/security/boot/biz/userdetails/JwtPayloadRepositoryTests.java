package org.springframework.security.boot.biz.userdetails;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtPayloadRepositoryTests {

    @Test void shouldReturnEmptyStringForDefaultIssueJwt() {
        JwtPayloadRepository repo = new JwtPayloadRepository() {};
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("user", "pass");
        assertEquals("", repo.issueJwt(token));
    }

    @Test void shouldReturnEmptyStringForDefaultIssueJwtWithUidAndClaims() {
        JwtPayloadRepository repo = new JwtPayloadRepository() {};
        assertEquals("", repo.issueJwt("uid", new HashMap<>()));
    }

    @Test void shouldReturnFalseForDefaultVerify() {
        JwtPayloadRepository repo = new JwtPayloadRepository() {};
        assertFalse(repo.verify("token", false));
    }

    @Test void shouldReturnNullForDefaultGetPayload() {
        JwtPayloadRepository repo = new JwtPayloadRepository() {};
        assertNull(repo.getPayload("token", false));
    }

    @Test void shouldReturnNullForDefaultGetPayloadWithToken() {
        JwtPayloadRepository repo = new JwtPayloadRepository() {};
        assertNull(repo.getPayload("token", false));
    }
}
