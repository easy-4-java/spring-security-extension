package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTests {

    @Test void shouldHaveConfigLocationDelimiters() {
        assertNotNull(StringUtils.CONFIG_LOCATION_DELIMITERS);
        assertTrue(StringUtils.CONFIG_LOCATION_DELIMITERS.contains(","));
    }

    @Test void shouldTokenizeString() {
        String[] tokens = StringUtils.tokenizeToStringArray("a,b;c d");
        assertEquals(4, tokens.length);
    }

    @Test void shouldInheritSpringStringUtils() {
        assertTrue(StringUtils.hasText("hello"));
        assertFalse(StringUtils.hasText(""));
    }
}
