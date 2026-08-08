package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceExceptionAdapterTests {

    private static class TestServiceExceptionAdapter extends AuthenticationServiceExceptionAdapter {
        public TestServiceExceptionAdapter(AuthResponseCode code, String msg) { super(code, msg); }
        public TestServiceExceptionAdapter(AuthResponseCode code, String msg, Throwable t) { super(code, msg, t); }
        public TestServiceExceptionAdapter(int code, String msg, Throwable t) { super(code, msg, t); }
        public TestServiceExceptionAdapter(int code, String msgKey, String msg, Throwable t) { super(code, msgKey, msg, t); }
    }

    @Test void shouldCreateWithAuthResponseCode() {
        TestServiceExceptionAdapter ex = new TestServiceExceptionAdapter(AuthResponseCode.SC_AUTHC_FAIL, "fail");
        assertEquals(10001, ex.getCode());
        assertEquals("spring.security.authc.fail", ex.getMsgKey());
    }

    @Test void shouldCreateWithAuthResponseCodeAndCause() {
        TestServiceExceptionAdapter ex = new TestServiceExceptionAdapter(AuthResponseCode.SC_AUTHC_FAIL, "fail", new RuntimeException());
        assertNotNull(ex.getCause());
    }

    @Test void shouldCreateWithIntCodeAndCause() {
        TestServiceExceptionAdapter ex = new TestServiceExceptionAdapter(9999, "fail", new RuntimeException());
        assertEquals(9999, ex.getCode());
        assertNull(ex.getMsgKey());
    }

    @Test void shouldCreateWithIntCodeMsgKeyAndCause() {
        TestServiceExceptionAdapter ex = new TestServiceExceptionAdapter(9999, "key", "fail", new RuntimeException());
        assertEquals(9999, ex.getCode());
        assertEquals("key", ex.getMsgKey());
    }
}
