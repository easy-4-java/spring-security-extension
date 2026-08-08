package org.springframework.security.boot.biz;

import javax.servlet.FilterChain;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class CustomWebSecurityExpressionHandlerAdditionalTests {

    @Test void shouldCreateSecurityExpressionRoot() {
        CustomWebSecurityExpressionHandler handler = new CustomWebSecurityExpressionHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1");
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        FilterChain noopChain = (req, res) -> {};
        FilterInvocation fi = new FilterInvocation(request, new MockHttpServletResponse(), noopChain);
        SecurityExpressionOperations ops = handler.createSecurityExpressionRoot(auth, fi);
        assertNotNull(ops);
    }
}
