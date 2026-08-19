package org.springframework.security.extension.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthConstantsTest {

    @Test
    void shouldHaveCorrectPrincipalAttributeNames() {
        assertEquals("uid", AuthConstants.UID);
        assertEquals("ukey", AuthConstants.UKEY);
        assertEquals("ucode", AuthConstants.UCODE);
        assertEquals("rid", AuthConstants.RID);
        assertEquals("rkey", AuthConstants.RKEY);
    }

    @Test
    void shouldHaveCorrectResponseStatusMarkers() {
        assertEquals("success", AuthConstants.RT_SUCCESS);
        assertEquals("fail", AuthConstants.RT_FAIL);
        assertEquals("error", AuthConstants.RT_ERROR);
    }
}
