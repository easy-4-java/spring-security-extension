package org.springframework.security.extension.property;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class SessionFixationPolicyTest {

    @Test
    void shouldHaveFourValues() {
        assertEquals(4, SessionFixationPolicy.values().length);
    }

    @Test
    void shouldContainChangeSessionId() {
        assertNotNull(SessionFixationPolicy.CHANGE_SESSION_ID);
    }

    @Test
    void shouldContainMigrateSession() {
        assertNotNull(SessionFixationPolicy.MIGRATE_SESSION);
    }

    @Test
    void shouldContainNewSession() {
        assertNotNull(SessionFixationPolicy.NEW_SESSION);
    }

    @Test
    void shouldContainNone() {
        assertNotNull(SessionFixationPolicy.NONE);
    }

    @Test
    void shouldBeEqualToSelfViaEqualsMethod() {
        for (SessionFixationPolicy policy : SessionFixationPolicy.values()) {
            assertTrue(policy.equals(policy));
        }
    }

    @Test
    void shouldNotBeEqualToDifferentPolicy() {
        assertFalse(SessionFixationPolicy.NONE.equals(SessionFixationPolicy.NEW_SESSION));
        assertFalse(SessionFixationPolicy.CHANGE_SESSION_ID.equals(SessionFixationPolicy.MIGRATE_SESSION));
    }

    @ParameterizedTest
    @EnumSource(SessionFixationPolicy.class)
    void shouldEqualSameConstant(SessionFixationPolicy policy) {
        SessionFixationPolicy same = SessionFixationPolicy.valueOf(policy.name());
        assertTrue(policy.equals(same));
    }

    @Test
    void shouldHaveCorrectOrdinals() {
        assertEquals(0, SessionFixationPolicy.CHANGE_SESSION_ID.ordinal());
        assertEquals(1, SessionFixationPolicy.MIGRATE_SESSION.ordinal());
        assertEquals(2, SessionFixationPolicy.NEW_SESSION.ordinal());
        assertEquals(3, SessionFixationPolicy.NONE.ordinal());
    }
}
