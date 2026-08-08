package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsAdditionalTests {

    @Test void shouldHaveDefaultDelimiters() {
        assertNotNull(StringUtils.CONFIG_LOCATION_DELIMITERS);
    }

    @Test void shouldTokenizeWithDefaultDelimiters() {
        String[] tokens = StringUtils.tokenizeToStringArray("a,b;c\td\ne");
        assertEquals(5, tokens.length);
    }

    @Test void shouldHandleNullTokenize() {
        String[] tokens = StringUtils.tokenizeToStringArray(null);
        assertEquals(0, tokens.length);
    }
}
