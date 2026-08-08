package org.springframework.security.boot.biz.filter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomCorsFilterTests {

    @Test void shouldCreateInstance() {
        CustomCorsFilter filter = new CustomCorsFilter();
        assertNotNull(filter);
    }
}
