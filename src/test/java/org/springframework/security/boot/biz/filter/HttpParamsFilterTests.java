package org.springframework.security.boot.biz.filter;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import static org.junit.jupiter.api.Assertions.*;

class HttpParamsFilterTests {

    @Test void shouldHaveRequestedUrlConstant() {
        assertEquals("CasRequestedUrl", HttpParamsFilter.REQUESTED_URL);
    }

    @Test void shouldStorePathInSession() throws Exception {
        HttpParamsFilter filter = new HttpParamsFilter();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setPathInfo("/some/path");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        filter.doFilter(request, response, chain);
        assertEquals("/some/path", request.getSession().getAttribute(HttpParamsFilter.REQUESTED_URL));
    }

    @Test void shouldCallInitAndDestroy() throws Exception {
        HttpParamsFilter filter = new HttpParamsFilter();
        assertDoesNotThrow(() -> filter.init(null));
        assertDoesNotThrow(() -> filter.destroy());
    }
}
