package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthResponseAdditionalTests {

    @Test void shouldCreateOfIntCodeStatusMessage() {
        AuthResponse<Object> r = AuthResponse.of(500, "fail", "error msg");
        assertEquals(500, r.getCode());
        assertEquals("fail", r.getStatus());
        assertEquals("error msg", r.getmessage());
    }

    @Test void shouldCreateOfIntCodeStatusMessageData() {
        AuthResponse<String> r = AuthResponse.of(500, "fail", "error msg", "data");
        assertEquals(500, r.getCode());
        assertEquals("fail", r.getStatus());
        assertEquals("error msg", r.getmessage());
        assertEquals("data", r.getData());
    }

    @Test void shouldCreateOfStringCodeStatusMessage() {
        AuthResponse<Object> r = AuthResponse.of("500", "fail", "error msg");
        assertEquals(500, r.getCode());
        assertEquals("fail", r.getStatus());
        assertEquals("error msg", r.getmessage());
    }

    @Test void shouldCreateOfIntCodeMessage() {
        AuthResponse<Object> r = AuthResponse.of(404, "Not Found");
        assertEquals(404, r.getCode());
        assertEquals("Not Found", r.getmessage());
    }

    @Test void shouldCreateOfStringCodeMessage() {
        AuthResponse<Object> r = AuthResponse.of("404", "Not Found");
        assertEquals(404, r.getCode());
        assertEquals("Not Found", r.getmessage());
    }

    @Test void shouldCreateSuccessWithIntCodeMessage() {
        AuthResponse<Object> r = AuthResponse.success(200, "ok");
        assertEquals(200, r.getCode());
        assertEquals("ok", r.getmessage());
    }

    @Test void shouldCreateFailWithIntCodeMessage() {
        AuthResponse<Object> r = AuthResponse.fail(1000, "fail msg");
        assertEquals(1000, r.getCode());
        assertEquals("fail", r.getStatus());
        assertEquals("fail msg", r.getmessage());
    }
}
