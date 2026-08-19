package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class CustomWebSecurityExpressionHandlerAdditionalTests {

    @Test void shouldCreateSecurityExpressionRoot() {
        CustomWebSecurityExpressionHandler handler = new CustomWebSecurityExpressionHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        RequestAuthorizationContext context = new RequestAuthorizationContext(request);
        SecurityExpressionOperations ops = handler.createSecurityExpressionRoot(auth, context);
        assertNotNull(ops);
    }
}
