package org.springframework.security.extension.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiCodeValueTest {

    @Test
    void shouldHaveCorrectSuccessCode() {
        assertEquals(200, ApiCodeValue.SC_SUCCESS);
    }

    @Test
    void shouldHaveCorrectFailCode() {
        assertEquals(1000, ApiCodeValue.SC_FAIL);
    }

    @Test
    void shouldHaveCorrectAuthenticationFailureCodes() {
        assertEquals(10001, ApiCodeValue.SC_AUTHC_FAIL);
        assertEquals(10002, ApiCodeValue.SC_AUTHC_METHOD_NOT_ALLOWED);
        assertEquals(10003, ApiCodeValue.SC_AUTHC_OVER_RETRY_REMIND);
        assertEquals(10004, ApiCodeValue.SC_AUTHC_CAPTCHA_REQUIRED);
        assertEquals(10005, ApiCodeValue.SC_AUTHC_CAPTCHA_EXPIRED);
        assertEquals(10006, ApiCodeValue.SC_AUTHC_CAPTCHA_INCORRECT);
        assertEquals(10007, ApiCodeValue.SC_AUTHC_ACCOUNT_NOT_FOUND);
        assertEquals(10008, ApiCodeValue.SC_AUTHC_ACCOUNT_DISABLED);
        assertEquals(10009, ApiCodeValue.SC_AUTHC_ACCOUNT_EXPIRED);
        assertEquals(10010, ApiCodeValue.SC_AUTHC_ACCOUNT_LOCKED);
        assertEquals(10011, ApiCodeValue.SC_AUTHC_CREDENTIALS_EXPIRED);
        assertEquals(10012, ApiCodeValue.SC_AUTHC_BAD_CREDENTIALS);
    }

    @Test
    void shouldHaveCorrectAuthorisationFailureCodes() {
        assertEquals(10020, ApiCodeValue.SC_AUTHZ_FAIL);
        assertEquals(10021, ApiCodeValue.SC_AUTHZ_TOKEN_ISSUED);
        assertEquals(10022, ApiCodeValue.SC_AUTHZ_TOKEN_REQUIRED);
        assertEquals(10023, ApiCodeValue.SC_AUTHZ_TOKEN_EXPIRED);
        assertEquals(10024, ApiCodeValue.SC_AUTHZ_TOKEN_INVALID);
        assertEquals(10025, ApiCodeValue.SC_AUTHZ_TOKEN_INCORRECT);
        assertEquals(10026, ApiCodeValue.SC_AUTHZ_CODE_REQUIRED);
        assertEquals(10027, ApiCodeValue.SC_AUTHZ_CODE_EXPIRED);
        assertEquals(10028, ApiCodeValue.SC_AUTHZ_CODE_INVALID);
        assertEquals(10029, ApiCodeValue.SC_AUTHZ_CODE_INCORRECT);
        assertEquals(10030, ApiCodeValue.SC_AUTHZ_THIRD_PARTY_SERVICE);
    }
}
