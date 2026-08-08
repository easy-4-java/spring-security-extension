package org.springframework.security.boot.biz.authentication.captcha;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SessionCaptchaResolverTests {

    @Test void shouldReturnFalseWhenCaptchaEmpty() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertFalse(resolver.validCaptcha(request, ""));
    }

    @Test void shouldReturnFalseWhenCaptchaNull() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertFalse(resolver.validCaptcha(request, null));
    }

    @Test void shouldReturnFalseWhenNoSessionCaptcha() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertFalse(resolver.validCaptcha(request, "abc"));
    }

    @Test void shouldValidateMatchingCaptcha() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        resolver.setCaptcha(request, response, "ABC123", new Date());
        assertTrue(resolver.validCaptcha(request, "ABC123"));
        assertTrue(resolver.validCaptcha(request, "abc123")); // case insensitive
    }

    @Test void shouldReturnFalseForNonMatchingCaptcha() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        resolver.setCaptcha(request, response, "ABC123", new Date());
        assertFalse(resolver.validCaptcha(request, "XYZ"));
    }

    @Test void shouldStoreCaptchaDate() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Date date = new Date();
        resolver.setCaptcha(request, response, "test", date);
        assertNotNull(request.getSession().getAttribute(SessionCaptchaResolver.KAPTCHA_DATE_SESSION_ATTRIBUTE_NAME));
    }

    @Test void shouldHandleNullCaptchaOnSet() {
        SessionCaptchaResolver resolver = new SessionCaptchaResolver();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertDoesNotThrow(() -> resolver.setCaptcha(request, response, null, null));
    }
}
