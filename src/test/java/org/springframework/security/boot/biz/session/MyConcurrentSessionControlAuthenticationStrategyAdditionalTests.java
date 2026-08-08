package org.springframework.security.boot.biz.session;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import static org.junit.jupiter.api.Assertions.*;

class MyConcurrentSessionControlAuthenticationStrategyAdditionalTests {

    @Test void shouldSetMessageSource() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        StaticMessageSource messageSource = new StaticMessageSource();
        assertDoesNotThrow(() -> strategy.setMessageSource(messageSource));
    }

    @Test void shouldRejectNullMessageSource() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        assertThrows(IllegalArgumentException.class, () -> strategy.setMessageSource(null));
    }

    @Test void shouldAcceptNegativeMaxSessions() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        assertDoesNotThrow(() -> strategy.setMaximumSessions(-1));
    }

    @Test void shouldAcceptPositiveMaxSessions() {
        SessionRegistry registry = new SessionRegistryImpl();
        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        assertDoesNotThrow(() -> strategy.setMaximumSessions(10));
    }
}
