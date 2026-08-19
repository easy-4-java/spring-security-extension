package org.springframework.security.extension.exception;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {

    // --- success factories ---

    @Test
    void shouldBuildSuccessWithMessage() {
        AuthResponse<String> resp = AuthResponse.success("ok");
        assertEquals(ApiCodeValue.SC_SUCCESS, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("ok", resp.getmessage());
        assertNull(resp.getData());
    }

    @Test
    void shouldBuildSuccessWithData() {
        AuthResponse<String> resp = AuthResponse.success("payload");
        assertEquals(ApiCodeValue.SC_SUCCESS, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
    }

    @Test
    void shouldBuildSuccessWithMessageAndData() {
        AuthResponse<String> resp = AuthResponse.success("done", "data");
        assertEquals(ApiCodeValue.SC_SUCCESS, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("done", resp.getmessage());
        assertEquals("data", resp.getData());
    }

    @Test
    void shouldBuildSuccessWithCodeAndMessage() {
        AuthResponse<String> resp = AuthResponse.success(201, "created");
        assertEquals(201, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("created", resp.getmessage());
    }

    // --- fail factories ---

    @Test
    void shouldBuildFailWithMessage() {
        AuthResponse<String> resp = AuthResponse.fail("bad");
        assertEquals(AuthResponseCode.SC_AUTHC_FAIL.getCode(), resp.getCode());
        assertEquals(AuthConstants.RT_ERROR, resp.getStatus());
        assertEquals("bad", resp.getmessage());
    }

    @Test
    void shouldBuildFailWithData() {
        AuthResponse<String> resp = AuthResponse.fail("detail");
        assertNotNull(resp);
        assertEquals(AuthResponseCode.SC_AUTHC_FAIL.getCode(), resp.getCode());
    }

    @Test
    void shouldBuildFailWithCodeAndMessage() {
        AuthResponse<String> resp = AuthResponse.fail(4001, "invalid");
        assertEquals(4001, resp.getCode());
        assertEquals(AuthConstants.RT_FAIL, resp.getStatus());
        assertEquals("invalid", resp.getmessage());
    }

    // --- of factories ---

    @Test
    void shouldBuildFromAuthResponseCode() {
        AuthResponse<Object> resp = AuthResponse.of(AuthResponseCode.SC_AUTHZ_FAIL);
        assertEquals(AuthResponseCode.SC_AUTHZ_FAIL.getCode(), resp.getCode());
        assertEquals(AuthResponseCode.SC_AUTHZ_FAIL.getStatus(), resp.getStatus());
    }

    @Test
    void shouldBuildFromAuthResponseCodeAndData() {
        AuthResponse<String> resp = AuthResponse.of(AuthResponseCode.SC_AUTHC_SUCCESS, "hello");
        assertEquals(AuthResponseCode.SC_AUTHC_SUCCESS.getCode(), resp.getCode());
        assertEquals("hello", resp.getData());
    }

    @Test
    void shouldBuildFromAuthResponseCodeMessageAndData() {
        AuthResponse<String> resp = AuthResponse.of(AuthResponseCode.SC_AUTHC_FAIL, "msg", "data");
        assertEquals(AuthResponseCode.SC_AUTHC_FAIL.getCode(), resp.getCode());
        assertEquals(AuthResponseCode.SC_AUTHC_FAIL.getStatus(), resp.getStatus());
        assertEquals("msg", resp.getmessage());
        assertEquals("data", resp.getData());
    }

    @Test
    void shouldBuildFromStringCodeAndMessage() {
        AuthResponse<Object> resp = AuthResponse.of("200", "ok");
        assertEquals(200, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
    }

    @Test
    void shouldThrowNumberFormatExceptionForInvalidStringCode() {
        assertThrows(NumberFormatException.class, () -> AuthResponse.of("abc", "msg"));
    }

    @Test
    void shouldBuildFromIntCodeAndMessage() {
        AuthResponse<Object> resp = AuthResponse.of(500, "error");
        assertEquals(500, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("error", resp.getmessage());
    }

    @Test
    void shouldBuildFromStringCodeStatusAndMessage() {
        AuthResponse<Object> resp = AuthResponse.of("10001", AuthConstants.RT_ERROR, "fail");
        assertEquals(10001, resp.getCode());
        assertEquals(AuthConstants.RT_ERROR, resp.getStatus());
        assertEquals("fail", resp.getmessage());
    }

    @Test
    void shouldBuildFromIntCodeStatusAndMessage() {
        AuthResponse<Object> resp = AuthResponse.of(403, AuthConstants.RT_FAIL, "forbidden");
        assertEquals(403, resp.getCode());
        assertEquals(AuthConstants.RT_FAIL, resp.getStatus());
        assertEquals("forbidden", resp.getmessage());
        assertNull(resp.getData());
    }

    @Test
    void shouldBuildFullyCustomisable() {
        AuthResponse<String> resp = AuthResponse.of(200, AuthConstants.RT_SUCCESS, "ok", "payload");
        assertEquals(200, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("ok", resp.getmessage());
        assertEquals("payload", resp.getData());
    }

    // --- toMap ---

    @Test
    void shouldSerializeToMap() {
        AuthResponse<String> resp = AuthResponse.of(200, AuthConstants.RT_SUCCESS, "ok", "data");
        Map<String, Object> map = resp.toMap();
        assertEquals(200, map.get("code"));
        assertEquals(AuthConstants.RT_SUCCESS, map.get("status"));
        assertEquals("ok", map.get("message"));
        assertEquals("data", map.get("data"));
        assertEquals(4, map.size());
    }

    @Test
    void shouldSerializeNullDataToMap() {
        AuthResponse<Object> resp = AuthResponse.success("ok");
        Map<String, Object> map = resp.toMap();
        assertNull(map.get("data"));
    }

    // --- constructors via protected access ---

    @Test
    void shouldBuildViaConstructorWithMessage() {
        AuthResponse<String> resp = new AuthResponse<>("hello");
        assertEquals(ApiCodeValue.SC_SUCCESS, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("hello", resp.getmessage());
    }

    @Test
    void shouldBuildViaProtectedConstructorWithCode() {
        AuthResponse<Object> resp = new AuthResponse<>(AuthResponseCode.SC_AUTHZ_FAIL);
        assertEquals(AuthResponseCode.SC_AUTHZ_FAIL.getCode(), resp.getCode());
        assertEquals(AuthResponseCode.SC_AUTHZ_FAIL.getStatus(), resp.getStatus());
        assertNull(resp.getmessage());
    }

    @Test
    void shouldBuildViaProtectedConstructorWithCodeAndData() {
        AuthResponse<String> resp = new AuthResponse<>(AuthResponseCode.SC_AUTHC_SUCCESS, "data");
        assertEquals(AuthResponseCode.SC_AUTHC_SUCCESS.getCode(), resp.getCode());
        assertEquals("data", resp.getData());
    }

    @Test
    void shouldBuildViaProtectedConstructorWithCodeMessageAndData() {
        AuthResponse<String> resp = new AuthResponse<>(AuthResponseCode.SC_AUTHC_FAIL, "err", "detail");
        assertEquals(AuthResponseCode.SC_AUTHC_FAIL.getCode(), resp.getCode());
        assertEquals(AuthResponseCode.SC_AUTHC_FAIL.getStatus(), resp.getStatus());
        assertEquals("err", resp.getmessage());
        assertEquals("detail", resp.getData());
    }

    @Test
    void shouldBuildViaProtectedConstructorWithIntCodeAndMessage() {
        AuthResponse<Object> resp = new AuthResponse<>(201, "created");
        assertEquals(201, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("created", resp.getmessage());
    }

    @Test
    void shouldBuildViaProtectedConstructorWithIntCodeStatusAndMessage() {
        AuthResponse<Object> resp = new AuthResponse<>(500, AuthConstants.RT_ERROR, "server error");
        assertEquals(500, resp.getCode());
        assertEquals(AuthConstants.RT_ERROR, resp.getStatus());
        assertEquals("server error", resp.getmessage());
    }

    @Test
    void shouldBuildViaProtectedConstructorWithIntCodeMessageAndData() {
        // (int, String, T) with T=String resolves to (int, String, String) which
        // matches the (code, status, message) overload when the third arg is String.
        // To call the (code, message, data) overload we use a non-String data type.
        AuthResponse<Integer> resp = new AuthResponse<>(200, "ok", 42);
        assertEquals(200, resp.getCode());
        assertEquals(AuthConstants.RT_SUCCESS, resp.getStatus());
        assertEquals("ok", resp.getmessage());
        assertEquals(42, resp.getData());
    }
}
