package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.boot.biz.exception.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class SecurityResponseUtilsTests {

    @Test void shouldHandleSuccess() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        Authentication auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken("u", "p");
        SecurityResponseUtils.handleSuccess(req, res, auth);
        assertEquals(200, res.getStatus());
        assertTrue(res.getContentAsString().contains("success"));
    }

    @Test void shouldHandleAuthenticationExceptionAdapter() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        AuthenticationException e = new AuthenticationCaptchaExpiredException("expired");
        SecurityResponseUtils.handleException(req, res, e);
        assertTrue(res.getContentAsString().contains("10005"));
    }

    @Test void shouldHandleUsernameNotFoundException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        SecurityResponseUtils.handleException(req, res, new UsernameNotFoundException("not found"));
        assertTrue(res.getContentAsString().contains("10007"));
    }

    @Test void shouldHandleBadCredentialsException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        SecurityResponseUtils.handleException(req, res, new BadCredentialsException("bad"));
        assertTrue(res.getContentAsString().contains("10012"));
    }

    @Test void shouldHandleDisabledException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        SecurityResponseUtils.handleException(req, res, new DisabledException("disabled"));
        assertTrue(res.getContentAsString().contains("10008"));
    }

    @Test void shouldHandleLockedException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        SecurityResponseUtils.handleException(req, res, new LockedException("locked"));
        assertTrue(res.getContentAsString().contains("10010"));
    }

    @Test void shouldHandleAccountExpiredException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        SecurityResponseUtils.handleException(req, res, new AccountExpiredException("expired"));
        assertTrue(res.getContentAsString().contains("10009"));
    }

    @Test void shouldHandleCredentialsExpiredException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        SecurityResponseUtils.handleException(req, res, new CredentialsExpiredException("expired"));
        assertTrue(res.getContentAsString().contains("10011"));
    }

    @Test void shouldHandleGenericAuthenticationException() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        SecurityResponseUtils.handleException(req, res, new AuthenticationException("generic") {});
        assertTrue(res.getContentAsString().contains("10001"));
    }
}
