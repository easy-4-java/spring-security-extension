package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

import static org.junit.jupiter.api.Assertions.*;

class CustomWebSecurityExpressionHandlerTests {

    @Test
    void shouldCreateInstance() {
        CustomWebSecurityExpressionHandler handler = new CustomWebSecurityExpressionHandler();
        assertNotNull(handler);
    }

    @Test
    void shouldSetTrustResolver() {
        CustomWebSecurityExpressionHandler handler = new CustomWebSecurityExpressionHandler();
        handler.setTrustResolver(new AuthenticationTrustResolverImpl());
        assertNotNull(handler);
    }

    @Test
    void shouldSetDefaultRolePrefix() {
        CustomWebSecurityExpressionHandler handler = new CustomWebSecurityExpressionHandler();
        handler.setDefaultRolePrefix("ROLE_");
        assertNotNull(handler);
    }
}
