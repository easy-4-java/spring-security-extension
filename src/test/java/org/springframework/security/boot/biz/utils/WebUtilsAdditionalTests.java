package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class WebUtilsAdditionalTests {

    @Test void shouldNotDetectGetAsObjectRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.addHeader("Content-Type", "application/json");
        assertFalse(WebUtils.isObjectRequest(req));
    }

    @Test void shouldNotDetectPostWithoutJsonAsObjectRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        req.addHeader("Content-Type", "text/html");
        assertFalse(WebUtils.isObjectRequest(req));
    }
}
