package org.springframework.security.extension.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseCodeTest {

    @Test
    void shouldHaveAuthenticationSuccessCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_SUCCESS;
        assertEquals(ApiCodeValue.SC_SUCCESS, code.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, code.getStatus());
        assertEquals("spring.security.authc.success", code.getMsgKey());
    }

    @Test
    void shouldHaveAuthenticationFailCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_FAIL;
        assertEquals(ApiCodeValue.SC_AUTHC_FAIL, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.fail", code.getMsgKey());
    }

    @Test
    void shouldHaveMethodNotAllowedCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_METHOD_NOT_ALLOWED;
        assertEquals(ApiCodeValue.SC_AUTHC_METHOD_NOT_ALLOWED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.method-not-supported", code.getMsgKey());
    }

    @Test
    void shouldHaveOverRetryRemindCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_OVER_RETRY_REMIND;
        assertEquals(ApiCodeValue.SC_AUTHC_OVER_RETRY_REMIND, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.over-retry-remind", code.getMsgKey());
    }

    @Test
    void shouldHaveCaptchaRequiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_CAPTCHA_REQUIRED;
        assertEquals(ApiCodeValue.SC_AUTHC_CAPTCHA_REQUIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.captcha.required", code.getMsgKey());
    }

    @Test
    void shouldHaveCaptchaExpiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_CAPTCHA_EXPIRED;
        assertEquals(ApiCodeValue.SC_AUTHC_CAPTCHA_EXPIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.captcha.expired", code.getMsgKey());
    }

    @Test
    void shouldHaveCaptchaIncorrectCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_CAPTCHA_INCORRECT;
        assertEquals(ApiCodeValue.SC_AUTHC_CAPTCHA_INCORRECT, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.captcha.incorrect", code.getMsgKey());
    }

    @Test
    void shouldHaveAccountNotFoundCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_ACCOUNT_NOT_FOUND;
        assertEquals(ApiCodeValue.SC_AUTHC_ACCOUNT_NOT_FOUND, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.principal.not-found", code.getMsgKey());
    }

    @Test
    void shouldHaveAccountDisabledCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_ACCOUNT_DISABLED;
        assertEquals(ApiCodeValue.SC_AUTHC_ACCOUNT_DISABLED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.principal.disabled", code.getMsgKey());
    }

    @Test
    void shouldHaveAccountExpiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_ACCOUNT_EXPIRED;
        assertEquals(ApiCodeValue.SC_AUTHC_ACCOUNT_EXPIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.principal.expired", code.getMsgKey());
    }

    @Test
    void shouldHaveAccountLockedCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_ACCOUNT_LOCKED;
        assertEquals(ApiCodeValue.SC_AUTHC_ACCOUNT_LOCKED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.principal.locked", code.getMsgKey());
    }

    @Test
    void shouldHaveCredentialsExpiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_CREDENTIALS_EXPIRED;
        assertEquals(ApiCodeValue.SC_AUTHC_CREDENTIALS_EXPIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.credentials.expired", code.getMsgKey());
    }

    @Test
    void shouldHaveBadCredentialsCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHC_BAD_CREDENTIALS;
        assertEquals(ApiCodeValue.SC_AUTHC_BAD_CREDENTIALS, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authc.credentials.incorrect", code.getMsgKey());
    }

    @Test
    void shouldHaveAuthorisationSuccessCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_SUCCESS;
        assertEquals(ApiCodeValue.SC_SUCCESS, code.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, code.getStatus());
        assertEquals("spring.security.authz.success", code.getMsgKey());
    }

    @Test
    void shouldHaveAuthorisationFailCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_FAIL;
        assertEquals(ApiCodeValue.SC_AUTHZ_FAIL, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.fail", code.getMsgKey());
    }

    @Test
    void shouldHaveTokenIssuedCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_TOKEN_ISSUED;
        assertEquals(ApiCodeValue.SC_AUTHZ_TOKEN_ISSUED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.token.issued", code.getMsgKey());
    }

    @Test
    void shouldHaveTokenRequiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_TOKEN_REQUIRED;
        assertEquals(ApiCodeValue.SC_AUTHZ_TOKEN_REQUIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.token.required", code.getMsgKey());
    }

    @Test
    void shouldHaveTokenExpiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_TOKEN_EXPIRED;
        assertEquals(ApiCodeValue.SC_AUTHZ_TOKEN_EXPIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.token.expired", code.getMsgKey());
    }

    @Test
    void shouldHaveTokenInvalidCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_TOKEN_INVALID;
        assertEquals(ApiCodeValue.SC_AUTHZ_TOKEN_INVALID, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.token.invalid", code.getMsgKey());
    }

    @Test
    void shouldHaveTokenIncorrectCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_TOKEN_INCORRECT;
        assertEquals(ApiCodeValue.SC_AUTHZ_TOKEN_INCORRECT, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.token.incorrect", code.getMsgKey());
    }

    @Test
    void shouldHaveCodeRequiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_CODE_REQUIRED;
        assertEquals(ApiCodeValue.SC_AUTHZ_CODE_REQUIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.code.required", code.getMsgKey());
    }

    @Test
    void shouldHaveCodeExpiredCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_CODE_EXPIRED;
        assertEquals(ApiCodeValue.SC_AUTHZ_CODE_EXPIRED, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.code.expired", code.getMsgKey());
    }

    @Test
    void shouldHaveCodeInvalidCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_CODE_INVALID;
        assertEquals(ApiCodeValue.SC_AUTHZ_CODE_INVALID, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.code.invalid", code.getMsgKey());
    }

    @Test
    void shouldHaveCodeIncorrectCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_CODE_INCORRECT;
        assertEquals(ApiCodeValue.SC_AUTHZ_CODE_INCORRECT, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.code.incorrect", code.getMsgKey());
    }

    @Test
    void shouldHaveThirdPartyServiceCode() {
        AuthResponseCode code = AuthResponseCode.SC_AUTHZ_THIRD_PARTY_SERVICE;
        assertEquals(ApiCodeValue.SC_AUTHZ_THIRD_PARTY_SERVICE, code.getCode());
        assertEquals(AuthConstants.RT_ERROR, code.getStatus());
        assertEquals("spring.security.authz.server.error", code.getMsgKey());
    }

    @ParameterizedTest
    @EnumSource(AuthResponseCode.class)
    void shouldHaveNonNullFields(AuthResponseCode code) {
        assertNotNull(code.getStatus());
        assertNotNull(code.getMsgKey());
    }

    @Test
    void shouldHave25EnumConstants() {
        assertEquals(25, AuthResponseCode.values().length);
    }
}
