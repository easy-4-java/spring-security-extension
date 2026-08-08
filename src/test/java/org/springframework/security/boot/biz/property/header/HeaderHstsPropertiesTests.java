package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderHstsPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetIncludeSubDomains() {
        HeaderHstsProperties props = new HeaderHstsProperties();
        props.setIncludeSubDomains(true);
        assertTrue(props.isIncludeSubDomains());
    }
}
