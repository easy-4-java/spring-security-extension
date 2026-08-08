package org.springframework.security.boot.biz.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthConstantsAdditionalTests {

    @Test void shouldHaveAllConstants() {
        assertNotNull(AuthConstants.UID);
        assertNotNull(AuthConstants.UKEY);
        assertNotNull(AuthConstants.UCODE);
        assertNotNull(AuthConstants.RID);
        assertNotNull(AuthConstants.RKEY);
        assertNotNull(AuthConstants.RT_SUCCESS);
        assertNotNull(AuthConstants.RT_FAIL);
        assertNotNull(AuthConstants.RT_ERROR);
    }
}
