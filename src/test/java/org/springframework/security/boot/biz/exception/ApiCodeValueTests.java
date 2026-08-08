package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiCodeValueTests {
    @Test void shouldHaveSuccessCode() { assertEquals(200, ApiCodeValue.SC_SUCCESS); }
    @Test void shouldHaveFailCode() { assertEquals(1000, ApiCodeValue.SC_FAIL); }
    @Test void shouldHaveAuthcFailCode() { assertEquals(10001, ApiCodeValue.SC_AUTHC_FAIL); }
    @Test void shouldHaveMethodNotAllowedCode() { assertEquals(10002, ApiCodeValue.SC_AUTHC_METHOD_NOT_ALLOWED); }
    @Test void shouldHaveOverRetryCode() { assertEquals(10003, ApiCodeValue.SC_AUTHC_OVER_RETRY_REMIND); }
    @Test void shouldHaveCaptchaRequiredCode() { assertEquals(10004, ApiCodeValue.SC_AUTHC_CAPTCHA_REQUIRED); }
    @Test void shouldHaveCaptchaExpiredCode() { assertEquals(10005, ApiCodeValue.SC_AUTHC_CAPTCHA_EXPIRED); }
    @Test void shouldHaveCaptchaIncorrectCode() { assertEquals(10006, ApiCodeValue.SC_AUTHC_CAPTCHA_INCORRECT); }
    @Test void shouldHaveAccountNotFoundCode() { assertEquals(10007, ApiCodeValue.SC_AUTHC_ACCOUNT_NOT_FOUND); }
    @Test void shouldHaveAccountDisabledCode() { assertEquals(10008, ApiCodeValue.SC_AUTHC_ACCOUNT_DISABLED); }
    @Test void shouldHaveAccountExpiredCode() { assertEquals(10009, ApiCodeValue.SC_AUTHC_ACCOUNT_EXPIRED); }
    @Test void shouldHaveAccountLockedCode() { assertEquals(10010, ApiCodeValue.SC_AUTHC_ACCOUNT_LOCKED); }
    @Test void shouldHaveCredentialsExpiredCode() { assertEquals(10011, ApiCodeValue.SC_AUTHC_CREDENTIALS_EXPIRED); }
    @Test void shouldHaveBadCredentialsCode() { assertEquals(10012, ApiCodeValue.SC_AUTHC_BAD_CREDENTIALS); }
    @Test void shouldHaveAuthzFailCode() { assertEquals(10020, ApiCodeValue.SC_AUTHZ_FAIL); }
    @Test void shouldHaveTokenIssuedCode() { assertEquals(10021, ApiCodeValue.SC_AUTHZ_TOKEN_ISSUED); }
    @Test void shouldHaveTokenRequiredCode() { assertEquals(10022, ApiCodeValue.SC_AUTHZ_TOKEN_REQUIRED); }
    @Test void shouldHaveTokenExpiredCode() { assertEquals(10023, ApiCodeValue.SC_AUTHZ_TOKEN_EXPIRED); }
    @Test void shouldHaveTokenInvalidCode() { assertEquals(10024, ApiCodeValue.SC_AUTHZ_TOKEN_INVALID); }
    @Test void shouldHaveTokenIncorrectCode() { assertEquals(10025, ApiCodeValue.SC_AUTHZ_TOKEN_INCORRECT); }
    @Test void shouldHaveCodeRequiredCode() { assertEquals(10026, ApiCodeValue.SC_AUTHZ_CODE_REQUIRED); }
    @Test void shouldHaveCodeExpiredCode() { assertEquals(10027, ApiCodeValue.SC_AUTHZ_CODE_EXPIRED); }
    @Test void shouldHaveCodeInvalidCode() { assertEquals(10028, ApiCodeValue.SC_AUTHZ_CODE_INVALID); }
    @Test void shouldHaveCodeIncorrectCode() { assertEquals(10029, ApiCodeValue.SC_AUTHZ_CODE_INCORRECT); }
    @Test void shouldHaveThirdPartyServiceCode() { assertEquals(10030, ApiCodeValue.SC_AUTHZ_THIRD_PARTY_SERVICE); }
}
