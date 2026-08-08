package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostLoginRequestTests {

    @Test void shouldCreateWithAllFields() {
        PostLoginRequest req = new PostLoginRequest("user", "pass", "captcha");
        assertEquals("user", req.getUsername());
        assertEquals("pass", req.getPassword());
        assertEquals("captcha", req.getCaptcha());
    }

    @Test void shouldSetUsername() {
        PostLoginRequest req = new PostLoginRequest("u", "p", "c");
        req.setUsername("newUser");
        assertEquals("newUser", req.getUsername());
    }

    @Test void shouldSetPassword() {
        PostLoginRequest req = new PostLoginRequest("u", "p", "c");
        req.setPassword("newPass");
        assertEquals("newPass", req.getPassword());
    }

    @Test void shouldSetCaptcha() {
        PostLoginRequest req = new PostLoginRequest("u", "p", "c");
        req.setCaptcha("newCaptcha");
        assertEquals("newCaptcha", req.getCaptcha());
    }
}
