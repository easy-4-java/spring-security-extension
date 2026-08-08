package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthResponseCodeTests {

    @Test void shouldHaveAllEnumValues() {
        AuthResponseCode[] values = AuthResponseCode.values();
        assertTrue(values.length >= 20);
    }

    @Test void shouldGetCodeFromSuccess() {
        assertEquals(200, AuthResponseCode.SC_AUTHC_SUCCESS.getCode());
        assertEquals("success", AuthResponseCode.SC_AUTHC_SUCCESS.getStatus());
    }

    @Test void shouldGetCodeFromFail() {
        assertEquals(10001, AuthResponseCode.SC_AUTHC_FAIL.getCode());
    }

    @Test void shouldGetMsgKey() {
        assertEquals("spring.security.authc.success", AuthResponseCode.SC_AUTHC_SUCCESS.getMsgKey());
    }

    @Test void shouldGetStatus() {
        assertEquals("error", AuthResponseCode.SC_AUTHC_FAIL.getStatus());
    }

    @Test void shouldHaveTokenCodes() {
        assertEquals(10022, AuthResponseCode.SC_AUTHZ_TOKEN_REQUIRED.getCode());
        assertEquals(10023, AuthResponseCode.SC_AUTHZ_TOKEN_EXPIRED.getCode());
        assertEquals(10024, AuthResponseCode.SC_AUTHZ_TOKEN_INVALID.getCode());
        assertEquals(10025, AuthResponseCode.SC_AUTHZ_TOKEN_INCORRECT.getCode());
    }

    @Test void shouldHaveCodeCodes() {
        assertEquals(10026, AuthResponseCode.SC_AUTHZ_CODE_REQUIRED.getCode());
        assertEquals(10027, AuthResponseCode.SC_AUTHZ_CODE_EXPIRED.getCode());
        assertEquals(10028, AuthResponseCode.SC_AUTHZ_CODE_INVALID.getCode());
        assertEquals(10029, AuthResponseCode.SC_AUTHZ_CODE_INCORRECT.getCode());
    }
}
