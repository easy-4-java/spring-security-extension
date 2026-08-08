package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderContentTypeOptionsPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderContentTypeOptionsProperties props = new HeaderContentTypeOptionsProperties();
        assertNotNull(props);
    }
}
