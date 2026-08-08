package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTests {

    @Test void shouldCreateSuccessWithMessage() {
        AuthResponse<String> r = AuthResponse.success("ok");
        assertEquals(200, r.getCode());
        assertEquals("success", r.getStatus());
        assertEquals("ok", r.getmessage());
    }

    @Test void shouldCreateSuccessWithData() {
        AuthResponse<String> r = AuthResponse.success("data");
        assertNotNull(r);
    }

    @Test void shouldCreateSuccessWithMessageAndData() {
        AuthResponse<String> r = AuthResponse.success("msg", "data");
        assertEquals(200, r.getCode());
    }

    @Test void shouldCreateSuccessWithCodeAndMessage() {
        AuthResponse<String> r = AuthResponse.success(200, "ok");
        assertEquals(200, r.getCode());
    }

    @Test void shouldCreateFailWithMessage() {
        AuthResponse<String> r = AuthResponse.fail("fail");
        assertEquals(10001, r.getCode());
    }

    @Test void shouldCreateFailWithData() {
        AuthResponse<String> r = AuthResponse.fail("data");
        assertNotNull(r);
    }

    @Test void shouldCreateFailWithCodeAndMessage() {
        AuthResponse<String> r = AuthResponse.fail(1000, "fail");
        assertEquals(1000, r.getCode());
    }

    @Test void shouldCreateOfResponseCode() {
        AuthResponse<Object> r = AuthResponse.of(AuthResponseCode.SC_AUTHC_FAIL);
        assertEquals(10001, r.getCode());
    }

    @Test void shouldCreateOfResponseCodeAndData() {
        AuthResponse<String> r = AuthResponse.of(AuthResponseCode.SC_AUTHC_FAIL, "data");
        assertNotNull(r.getData());
    }

    @Test void shouldCreateOfResponseCodeMessageAndData() {
        AuthResponse<String> r = AuthResponse.of(AuthResponseCode.SC_AUTHC_FAIL, "msg", "data");
        assertEquals(10001, r.getCode());
    }

    @Test void shouldCreateOfStringCodeAndMessage() {
        AuthResponse<String> r = AuthResponse.of("200", "ok");
        assertEquals(200, r.getCode());
    }

    @Test void shouldCreateOfIntCodeAndMessage() {
        AuthResponse<String> r = AuthResponse.of(200, "ok");
        assertEquals(200, r.getCode());
    }

    @Test void shouldCreateOfStringCodeStatusMessage() {
        AuthResponse<String> r = AuthResponse.of("200", "success", "ok");
        assertEquals(200, r.getCode());
    }

    @Test void shouldCreateOfIntCodeStatusMessage() {
        AuthResponse<String> r = AuthResponse.of(200, "success", "ok");
        assertEquals(200, r.getCode());
    }

    @Test void shouldCreateOfIntCodeStatusMessageData() {
        AuthResponse<String> r = AuthResponse.of(200, "success", "ok", "data");
        assertEquals("data", r.getData());
    }

    @Test void shouldGetCode() {
        AuthResponse<String> r = AuthResponse.of(500, "error", "msg");
        assertEquals(500, r.getCode());
    }

    @Test void shouldGetStatus() {
        AuthResponse<String> r = AuthResponse.of(500, "error", "msg");
        assertEquals("error", r.getStatus());
    }

    @Test void shouldGetMessage() {
        AuthResponse<String> r = AuthResponse.of(500, "error", "test msg");
        assertEquals("test msg", r.getmessage());
    }

    @Test void shouldGetData() {
        AuthResponse<String> r = AuthResponse.of(200, "success", "msg", "payload");
        assertEquals("payload", r.getData());
    }

    @Test void shouldConvertToMap() {
        AuthResponse<String> r = AuthResponse.of(200, "success", "ok", "data");
        Map<String, Object> map = r.toMap();
        assertEquals(200, map.get("code"));
        assertEquals("success", map.get("status"));
        assertEquals("ok", map.get("message"));
        assertEquals("data", map.get("data"));
    }

    @Test void shouldCreateWithStringConstructor() {
        AuthResponse<Object> r = new AuthResponse<>("hello");
        assertEquals(200, r.getCode());
        assertEquals("success", r.getStatus());
    }
}
