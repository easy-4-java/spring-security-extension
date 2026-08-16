package org.springframework.security.boot.biz.authentication;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.security.boot.biz.authentication.captcha.NullCaptchaResolver;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestAuthenticationProcessingFilterTests {

    @Test void shouldCreateWithObjectMapper() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        assertNotNull(filter);
    }

    @Test void shouldCreateWithObjectMapperAndMatcher() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper(), new AntPathRequestMatcher("/auth"));
        assertNotNull(filter);
    }

    @Test void shouldHaveDefaultConstants() {
        assertEquals("username", PostRequestAuthenticationProcessingFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        assertEquals("password", PostRequestAuthenticationProcessingFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
        assertEquals("captcha", PostRequestAuthenticationProcessingFilter.SPRING_SECURITY_FORM_CAPTCHA_KEY);
        assertEquals("securityLoginFailureRetries", PostRequestAuthenticationProcessingFilter.DEFAULT_RETRY_TIMES_KEY_ATTRIBUTE_NAME);
    }

    @Test void shouldSetAndGetUsernameParameter() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        filter.setUsernameParameter("user");
        assertEquals("user", filter.getUsernameParameter());
    }

    @Test void shouldSetAndGetPasswordParameter() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        filter.setPasswordParameter("pass");
        assertEquals("pass", filter.getPasswordParameter());
    }

    @Test void shouldRejectEmptyUsernameParameter() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        assertThrows(IllegalArgumentException.class, () -> filter.setUsernameParameter(""));
    }

    @Test void shouldRejectEmptyPasswordParameter() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        assertThrows(IllegalArgumentException.class, () -> filter.setPasswordParameter(""));
    }

    @Test void shouldSetAndGetCaptchaRequired() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        assertFalse(filter.isCaptchaRequired());
        filter.setCaptchaRequired(true);
        assertTrue(filter.isCaptchaRequired());
    }

    @Test void shouldSetAndGetCaptchaResolver() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        NullCaptchaResolver resolver = new NullCaptchaResolver();
        filter.setCaptchaResolver(resolver);
        assertSame(resolver, filter.getCaptchaResolver());
    }

    @Test void shouldSetAndGetCaptchaParameter() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        filter.setCaptchaParameter("cap");
        assertEquals("cap", filter.getCaptchaParameter());
    }

    @Test void shouldSetAndGetFailureCounter() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        filter.setFailureCounter(counter);
        assertSame(counter, filter.getFailureCounter());
    }

    @Test void shouldSetAndGetRetryTimesKeyAttribute() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        filter.setRetryTimesKeyAttribute("custom");
        assertEquals("custom", filter.getRetryTimesKeyAttribute());
    }

    @Test void shouldSetAndGetRetryTimesWhenAccessDenied() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        filter.setRetryTimesWhenAccessDenied(5);
        assertEquals(5, filter.getRetryTimesWhenAccessDenied());
    }

    @Test void shouldSetAndGetPostOnly() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        assertTrue(filter.isPostOnly());
        filter.setPostOnly(false);
        assertFalse(filter.isPostOnly());
    }

    @Test void shouldGetObjectMapper() {
        ObjectMapper mapper = new JsonMapper();
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(mapper);
        assertSame(mapper, filter.getObjectMapper());
    }

    @Test void shouldHaveDefaultRetryTimes() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        assertEquals(3, filter.getRetryTimesWhenAccessDenied());
    }

    @Test void shouldHaveDefaultRetryTimesKeyAttribute() {
        PostRequestAuthenticationProcessingFilter filter =
                new PostRequestAuthenticationProcessingFilter(new JsonMapper());
        assertEquals("securityLoginFailureRetries", filter.getRetryTimesKeyAttribute());
    }
}
