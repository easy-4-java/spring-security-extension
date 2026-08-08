package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityFailureRetryPropertiesAdditionalTests {

    @Test void shouldSetAndGetRetryTimesKeyParameter() {
        SecurityFailureRetryProperties props = new SecurityFailureRetryProperties();
        props.setRetryTimesKeyParameter("custom");
        assertEquals("custom", props.getRetryTimesKeyParameter());
    }

    @Test void shouldSetAndGetRetryTimesKeyAttribute() {
        SecurityFailureRetryProperties props = new SecurityFailureRetryProperties();
        props.setRetryTimesKeyAttribute("customAttr");
        assertEquals("customAttr", props.getRetryTimesKeyAttribute());
    }

    @Test void shouldSetAndGetRetryTimesWhenAccessDenied() {
        SecurityFailureRetryProperties props = new SecurityFailureRetryProperties();
        props.setRetryTimesWhenAccessDenied(5);
        assertEquals(5, props.getRetryTimesWhenAccessDenied());
    }
}
