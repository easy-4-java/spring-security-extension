package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthConstantsTests {
    @Test void shouldHaveUid() { assertEquals("uid", AuthConstants.UID); }
    @Test void shouldHaveUkey() { assertEquals("ukey", AuthConstants.UKEY); }
    @Test void shouldHaveUcode() { assertEquals("ucode", AuthConstants.UCODE); }
    @Test void shouldHaveRid() { assertEquals("rid", AuthConstants.RID); }
    @Test void shouldHaveRkey() { assertEquals("rkey", AuthConstants.RKEY); }
    @Test void shouldHaveRtSuccess() { assertEquals("success", AuthConstants.RT_SUCCESS); }
    @Test void shouldHaveRtFail() { assertEquals("fail", AuthConstants.RT_FAIL); }
    @Test void shouldHaveRtError() { assertEquals("error", AuthConstants.RT_ERROR); }
}
