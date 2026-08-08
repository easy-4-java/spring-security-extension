package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionFixationPolicyTests {

    @Test void shouldHaveAllValues() {
        SessionFixationPolicy[] values = SessionFixationPolicy.values();
        assertEquals(4, values.length);
    }

    @Test void shouldEqualSelf() {
        assertTrue(SessionFixationPolicy.CHANGE_SESSION_ID.equals(SessionFixationPolicy.CHANGE_SESSION_ID));
    }

    @Test void shouldNotEqualOther() {
        assertFalse(SessionFixationPolicy.CHANGE_SESSION_ID.equals(SessionFixationPolicy.MIGRATE_SESSION));
    }

    @Test void shouldValueOf() {
        assertEquals(SessionFixationPolicy.NEW_SESSION, SessionFixationPolicy.valueOf("NEW_SESSION"));
    }
}
