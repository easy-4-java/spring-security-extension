package org.springframework.security.boot.biz.authentication.captcha;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NullCaptchaResolverTests {

    @Test void shouldAlwaysReturnTrue() {
        NullCaptchaResolver resolver = new NullCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertTrue(resolver.validCaptcha(request, "anything"));
    }

    @Test void shouldDoNothingOnSetCaptcha() {
        NullCaptchaResolver resolver = new NullCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertDoesNotThrow(() -> resolver.setCaptcha(request, response, "text", new Date()));
    }
}
