package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatingFailureSessionCounterTests {

    @Test void shouldReturnZeroWhenNoSessionAttribute() {
        AuthenticatingFailureSessionCounter counter = new AuthenticatingFailureSessionCounter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertEquals(0, counter.get(request, response, "failureCount"));
    }

    @Test void shouldReturnCountFromSession() {
        AuthenticatingFailureSessionCounter counter = new AuthenticatingFailureSessionCounter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("failureCount", 3);
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertEquals(3, counter.get(request, response, "failureCount"));
    }

    @Test void shouldIncrementFromZero() {
        AuthenticatingFailureSessionCounter counter = new AuthenticatingFailureSessionCounter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        counter.increment(request, response, "failureCount");
        assertEquals(1, counter.get(request, response, "failureCount"));
    }

    @Test void shouldIncrementExisting() {
        AuthenticatingFailureSessionCounter counter = new AuthenticatingFailureSessionCounter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("failureCount", 2);
        MockHttpServletResponse response = new MockHttpServletResponse();
        counter.increment(request, response, "failureCount");
        assertEquals(3, counter.get(request, response, "failureCount"));
    }
}
