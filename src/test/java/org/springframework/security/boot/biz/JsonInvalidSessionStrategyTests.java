package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class JsonInvalidSessionStrategyTests {

    @Test
    void shouldWriteJsonResponseOnInvalidSession() throws Exception {
        JsonInvalidSessionStrategy strategy = new JsonInvalidSessionStrategy();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        strategy.onInvalidSessionDetected(request, response);
        String content = response.getContentAsString();
        assertNotNull(content);
        assertTrue(content.contains("Session Invalided"));
    }
}
