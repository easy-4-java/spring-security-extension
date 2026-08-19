package org.springframework.security.extension.authentication.captcha;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NullCaptchaResolverTest {

    private final NullCaptchaResolver resolver = new NullCaptchaResolver();

    @Test
    void shouldAlwaysReturnTrueForValidCaptcha() {
        assertTrue(resolver.validCaptcha(null, "anything"));
    }

    @Test
    void shouldReturnTrueForNullCapText() {
        assertTrue(resolver.validCaptcha(null, null));
    }

    @Test
    void shouldReturnTrueForEmptyCapText() {
        assertTrue(resolver.validCaptcha(null, ""));
    }

    @Test
    void shouldBeNoOpOnSetCaptcha() {
        // Should not throw any exception
        assertDoesNotThrow(() -> resolver.setCaptcha(null, null, "text", new Date()));
    }

    @Test
    void shouldBeCaptchaResolverInstance() {
        assertInstanceOf(CaptchaResolver.class, resolver);
    }
}
