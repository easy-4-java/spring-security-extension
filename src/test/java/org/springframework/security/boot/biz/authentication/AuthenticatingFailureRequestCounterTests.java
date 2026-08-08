package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatingFailureRequestCounterTests {

    @Test void shouldReturnZeroWhenNoParam() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertEquals(0, counter.get(request, response, "key"));
    }

    @Test void shouldReturnCountFromParam() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("failureRetries", "5");
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertEquals(5, counter.get(request, response, "key"));
    }

    @Test void shouldIncrementBeNoop() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        assertDoesNotThrow(() -> counter.increment(request, response, "key"));
    }

    @Test void shouldSetAndGetRetryTimesKeyParameter() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        counter.setRetryTimesKeyParameter("custom");
        assertEquals("custom", counter.getRetryTimesKeyParameter());
    }

    @Test void shouldHaveDefaultRetryTimesKeyParameter() {
        AuthenticatingFailureRequestCounter counter = new AuthenticatingFailureRequestCounter();
        assertEquals("failureRetries", counter.getRetryTimesKeyParameter());
    }
}
