package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class WebUtilsTests {

    @Test void shouldDetectPostRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        assertTrue(WebUtils.isPostRequest(req));
    }

    @Test void shouldNotDetectGetAsPost() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        assertFalse(WebUtils.isPostRequest(req));
    }

    @Test void shouldDetectAjaxRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("X-Requested-With", "XMLHttpRequest");
        assertTrue(WebUtils.isAjaxRequest(req));
    }

    @Test void shouldNotDetectNonAjaxRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        assertFalse(WebUtils.isAjaxRequest(req));
    }

    @Test void shouldDetectJsonContentType() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("Content-Type", "application/json");
        assertTrue(WebUtils.isContentTypeJson(req));
    }

    @Test void shouldNotDetectNonJsonContentType() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("Content-Type", "text/html");
        assertFalse(WebUtils.isContentTypeJson(req));
    }

    @Test void shouldDetectObjectRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        req.addHeader("Content-Type", "application/json");
        assertTrue(WebUtils.isObjectRequest(req));
    }

    @Test void shouldNotDetectGetObjectAsObjectRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.addHeader("Content-Type", "application/json");
        assertFalse(WebUtils.isObjectRequest(req));
    }

    @Test void shouldDetectAjaxResponse() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.addHeader("X-Requested-With", "XMLHttpRequest");
        assertTrue(WebUtils.isAjaxResponse(req));
    }

    @Test void shouldDetectPostAsAjaxResponse() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        req.addHeader("Content-Type", "application/json");
        assertTrue(WebUtils.isAjaxResponse(req));
    }
}
