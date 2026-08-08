package org.springframework.security.boot.biz.session;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import static org.junit.jupiter.api.Assertions.*;

class MyConcurrentSessionControlAuthenticationStrategyTests {

    @Test void shouldCreateWithRegistry() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        assertNotNull(strategy);
    }

    @Test void shouldSetExceptionIfMaximumExceeded() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        assertDoesNotThrow(() -> strategy.setExceptionIfMaximumExceeded(true));
    }

    @Test void shouldSetMaximumSessions() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        assertDoesNotThrow(() -> strategy.setMaximumSessions(5));
    }

    @Test void shouldRejectZeroMaxSessions() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        assertThrows(IllegalArgumentException.class, () -> strategy.setMaximumSessions(0));
    }
}
