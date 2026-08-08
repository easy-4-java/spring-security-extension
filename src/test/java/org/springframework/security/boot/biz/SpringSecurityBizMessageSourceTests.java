package org.springframework.security.boot.biz;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.MessageSourceAccessor;

import static org.junit.jupiter.api.Assertions.*;

class SpringSecurityBizMessageSourceTests {

    @Test
    void shouldCreateInstance() {
        SpringSecurityBizMessageSource source = new SpringSecurityBizMessageSource();
        assertNotNull(source);
    }

    @Test
    void shouldReturnAccessor() {
        MessageSourceAccessor accessor = SpringSecurityBizMessageSource.getAccessor();
        assertNotNull(accessor);
    }
}
