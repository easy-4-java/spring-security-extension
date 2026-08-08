package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationExceptionAdapterTests {

    // Use a concrete subclass for testing since it's abstract
    private static class TestExceptionAdapter extends AuthenticationExceptionAdapter {
        public TestExceptionAdapter(AuthResponseCode code, String msg) { super(code, msg); }
        public TestExceptionAdapter(AuthResponseCode code, String msg, Throwable t) { super(code, msg, t); }
        public TestExceptionAdapter(int code, String msg, Throwable t) { super(code, msg, t); }
        public TestExceptionAdapter(int code, String msgKey, String msg, Throwable t) { super(code, msgKey, msg, t); }
    }

    @Test void shouldCreateWithAuthResponseCode() {
        TestExceptionAdapter ex = new TestExceptionAdapter(AuthResponseCode.SC_AUTHC_FAIL, "fail");
        assertEquals(10001, ex.getCode());
        assertEquals("spring.security.authc.fail", ex.getMsgKey());
        assertEquals("fail", ex.getMessage());
    }

    @Test void shouldCreateWithAuthResponseCodeAndCause() {
        RuntimeException cause = new RuntimeException("root");
        TestExceptionAdapter ex = new TestExceptionAdapter(AuthResponseCode.SC_AUTHC_FAIL, "fail", cause);
        assertEquals(10001, ex.getCode());
        assertEquals(cause, ex.getCause());
    }

    @Test void shouldCreateWithIntCodeAndCause() {
        RuntimeException cause = new RuntimeException("root");
        TestExceptionAdapter ex = new TestExceptionAdapter(9999, "fail", cause);
        assertEquals(9999, ex.getCode());
        assertNull(ex.getMsgKey());
    }

    @Test void shouldCreateWithIntCodeMsgKeyAndCause() {
        RuntimeException cause = new RuntimeException("root");
        TestExceptionAdapter ex = new TestExceptionAdapter(9999, "key", "fail", cause);
        assertEquals(9999, ex.getCode());
        assertEquals("key", ex.getMsgKey());
    }
}
