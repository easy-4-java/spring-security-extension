package org.springframework.security.boot.biz.session;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyConcurrentSessionControlAuthenticationStrategyOnAuthTests {

    @Test
    void shouldAllowWhenUnderMaxSessions() {
        SessionRegistry registry = mock(SessionRegistry.class);
        when(registry.getAllSessions(any(), eq(false))).thenReturn(new ArrayList<>());

        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        strategy.setMaximumSessions(2);

        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertDoesNotThrow(() -> strategy.onAuthentication(auth, request, response));
    }

    @Test
    void shouldAllowWhenUnlimitedSessions() {
        SessionRegistry registry = mock(SessionRegistry.class);
        List<SessionInformation> sessions = new ArrayList<>();
        sessions.add(new SessionInformation("user", "session1", new Date()));
        sessions.add(new SessionInformation("user", "session2", new Date()));
        when(registry.getAllSessions(any(), eq(false))).thenReturn(sessions);

        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        strategy.setMaximumSessions(-1);

        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertDoesNotThrow(() -> strategy.onAuthentication(auth, request, response));
    }

    @Test
    void shouldThrowWhenMaxSessionsExceeded() {
        SessionRegistry registry = mock(SessionRegistry.class);
        SessionInformation session1 = new SessionInformation("user", "session1", new Date());
        SessionInformation session2 = new SessionInformation("user", "session2", new Date());
        List<SessionInformation> sessions = new ArrayList<>();
        sessions.add(session1);
        sessions.add(session2);
        when(registry.getAllSessions(any(), eq(false))).thenReturn(sessions);

        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        strategy.setMaximumSessions(1);
        strategy.setExceptionIfMaximumExceeded(true);

        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertThrows(SessionAuthenticationException.class, () ->
                strategy.onAuthentication(auth, request, response));
    }

    @Test
    void shouldExpireLeastRecentlyUsedSession() {
        SessionRegistry registry = mock(SessionRegistry.class);
        SessionInformation oldSession = new SessionInformation("user", "old-session", new Date(1000));
        SessionInformation newSession = new SessionInformation("user", "new-session", new Date(2000));
        List<SessionInformation> sessions = new ArrayList<>();
        sessions.add(oldSession);
        sessions.add(newSession);
        when(registry.getAllSessions(any(), eq(false))).thenReturn(sessions);

        MyConcurrentSessionControlAuthenticationStrategy strategy =
                new MyConcurrentSessionControlAuthenticationStrategy(registry);
        strategy.setMaximumSessions(1);
        strategy.setExceptionIfMaximumExceeded(false);

        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        assertDoesNotThrow(() -> strategy.onAuthentication(auth, request, response));
        assertTrue(oldSession.isExpired());
    }
}
