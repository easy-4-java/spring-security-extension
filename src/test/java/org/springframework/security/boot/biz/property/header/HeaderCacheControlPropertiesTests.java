package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderCacheControlPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderCacheControlProperties props = new HeaderCacheControlProperties();
        assertNotNull(props);
    }
}
