package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class HeaderHpkpPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetIncludeSubDomains() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        props.setIncludeSubDomains(true);
        assertTrue(props.isIncludeSubDomains());
    }

    @Test void shouldSetAndGetReportUri() {
        HeaderHpkpProperties props = new HeaderHpkpProperties();
        props.setReportUri("https://example.com/report");
        assertEquals("https://example.com/report", props.getReportUri());
    }
}
