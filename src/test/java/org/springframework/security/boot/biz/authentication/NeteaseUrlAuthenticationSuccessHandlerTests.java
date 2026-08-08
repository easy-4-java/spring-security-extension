package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NeteaseUrlAuthenticationSuccessHandlerTests {

    @Test void shouldCreateDefault() {
        NeteaseUrlAuthenticationSuccessHandler handler = new NeteaseUrlAuthenticationSuccessHandler();
        assertNotNull(handler);
    }

    @Test void shouldCreateWithDefaultUrl() {
        NeteaseUrlAuthenticationSuccessHandler handler = new NeteaseUrlAuthenticationSuccessHandler("/home");
        assertNotNull(handler);
    }
}
