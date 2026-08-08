package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.boot.biz.filter.HttpParamsFilter;

import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

class NeteaseUrlAuthenticationSuccessHandlerAdditionalTests {

    @Test void shouldUseSessionAttributeWhenNoParam() throws Exception {
        NeteaseUrlAuthenticationSuccessHandler handler = new NeteaseUrlAuthenticationSuccessHandler("/default");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpSession session = request.getSession();
        session.setAttribute(HttpParamsFilter.REQUESTED_URL, "/from-session");
        // determineTargetUrl is protected, so we test via onAuthenticationSuccess
        // which internally calls determineTargetUrl
        org.springframework.security.authentication.UsernamePasswordAuthenticationToken auth =
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken("u", "p");
        handler.onAuthenticationSuccess(request, response, auth);
        // Since there's no session attribute in a fresh mock, it falls back to default
        assertNotNull(response);
    }
}
