package org.springframework.security.extension.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostLoginRequestTest {

    @Test
    void shouldCreateInstanceViaConstructor() {
        PostLoginRequest req = new PostLoginRequest("alice", "secret", "abc123");
        assertEquals("alice", req.getUsername());
        assertEquals("secret", req.getPassword());
        assertEquals("abc123", req.getCaptcha());
    }

    @Test
    void shouldAcceptNullCaptcha() {
        PostLoginRequest req = new PostLoginRequest("bob", "pw", null);
        assertNull(req.getCaptcha());
    }

    @Test
    void shouldSetUsername() {
        PostLoginRequest req = new PostLoginRequest("a", "b", "c");
        req.setUsername("newUser");
        assertEquals("newUser", req.getUsername());
    }

    @Test
    void shouldSetPassword() {
        PostLoginRequest req = new PostLoginRequest("a", "b", "c");
        req.setPassword("newPw");
        assertEquals("newPw", req.getPassword());
    }

    @Test
    void shouldSetCaptcha() {
        PostLoginRequest req = new PostLoginRequest("a", "b", "c");
        req.setCaptcha("newCaptcha");
        assertEquals("newCaptcha", req.getCaptcha());
    }

    @Test
    void shouldSetCaptchaToNull() {
        PostLoginRequest req = new PostLoginRequest("a", "b", "c");
        req.setCaptcha(null);
        assertNull(req.getCaptcha());
    }
}
