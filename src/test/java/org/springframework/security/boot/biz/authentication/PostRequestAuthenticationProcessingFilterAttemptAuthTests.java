package org.springframework.security.boot.biz.authentication;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.boot.biz.authentication.captcha.CaptchaResolver;
import org.springframework.security.boot.biz.authentication.captcha.NullCaptchaResolver;
import org.springframework.security.boot.biz.exception.AuthenticationCaptchaIncorrectException;
import org.springframework.security.boot.biz.exception.AuthenticationCaptchaNotFoundException;
import org.springframework.security.boot.biz.exception.AuthenticationOverRetryRemindException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostRequestAuthenticationProcessingFilterAttemptAuthTests {

    private final ObjectMapper objectMapper = new JsonMapper();

    @Test
    void shouldAttemptAuthenticationWithJsonRequest() throws Exception {
        PostRequestAuthenticationProcessingFilter filter = new PostRequestAuthenticationProcessingFilter(objectMapper);
        filter.setAuthenticationManager(auth -> auth);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("application/json");
        request.setContent("{\"username\":\"user\",\"password\":\"pass\"}".getBytes());
        MockHttpServletResponse response = new MockHttpServletResponse();

        Authentication result = filter.attemptAuthentication(request, response);
        assertNotNull(result);
        assertTrue(result instanceof UsernamePasswordAuthenticationToken);
    }

    @Test
    void shouldAttemptAuthenticationWithFormRequest() throws Exception {
        PostRequestAuthenticationProcessingFilter filter = new PostRequestAuthenticationProcessingFilter(objectMapper);
        filter.setAuthenticationManager(auth -> auth);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("application/x-www-form-urlencoded");
        request.setParameter("username", "user");
        request.setParameter("password", "pass");
        MockHttpServletResponse response = new MockHttpServletResponse();

        Authentication result = filter.attemptAuthentication(request, response);
        assertNotNull(result);
        assertTrue(result instanceof UsernamePasswordAuthenticationToken);
    }

    @Test
    void shouldRejectWhenCaptchaRequiredButMissing() {
        PostRequestAuthenticationProcessingFilter filter = new PostRequestAuthenticationProcessingFilter(objectMapper);
        filter.setCaptchaRequired(true);
        filter.setCaptchaResolver(new NullCaptchaResolver());
        filter.setAuthenticationManager(auth -> auth);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("application/json");
        request.setContent("{\"username\":\"user\",\"password\":\"pass\"}".getBytes());
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertThrows(AuthenticationCaptchaNotFoundException.class, () ->
                filter.attemptAuthentication(request, response));
    }

    @Test
    void shouldRejectWhenCaptchaIncorrect() {
        CaptchaResolver resolver = mock(CaptchaResolver.class);
        when(resolver.validCaptcha(any(), any())).thenReturn(false);

        PostRequestAuthenticationProcessingFilter filter = new PostRequestAuthenticationProcessingFilter(objectMapper);
        filter.setCaptchaRequired(true);
        filter.setCaptchaResolver(resolver);
        filter.setAuthenticationManager(auth -> auth);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("application/json");
        request.setContent("{\"username\":\"user\",\"password\":\"pass\",\"captcha\":\"wrong\"}".getBytes());
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertThrows(AuthenticationCaptchaIncorrectException.class, () ->
                filter.attemptAuthentication(request, response));
    }

    @Test
    void shouldRejectWhenOverRetryLimit() {
        PostRequestAuthenticationProcessingFilter filter = new PostRequestAuthenticationProcessingFilter(objectMapper);
        filter.setAuthenticationManager(auth -> auth);

        AuthenticatingFailureRequestCounter counter = mock(AuthenticatingFailureRequestCounter.class);
        when(counter.get(any(), any(), any())).thenReturn(filter.getRetryTimesWhenAccessDenied());
        filter.setFailureCounter(counter);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setContentType("application/json");
        request.setContent("{\"username\":\"user\",\"password\":\"pass\"}".getBytes());
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertThrows(AuthenticationOverRetryRemindException.class, () ->
                filter.attemptAuthentication(request, response));
    }

    @Test
    void shouldCreateAuthenticationToken() {
        PostRequestAuthenticationProcessingFilter filter = new PostRequestAuthenticationProcessingFilter(objectMapper);
        Authentication token = filter.authenticationToken("user", "pass");
        assertNotNull(token);
        assertTrue(token instanceof UsernamePasswordAuthenticationToken);
        assertEquals("user", token.getPrincipal());
        assertEquals("pass", token.getCredentials());
    }
}
