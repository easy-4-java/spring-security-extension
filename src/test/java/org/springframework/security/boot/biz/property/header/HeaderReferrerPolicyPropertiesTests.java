package org.springframework.security.boot.biz.property.header;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeaderReferrerPolicyPropertiesTests {

    @Test void shouldCreateInstance() {
        HeaderReferrerPolicyProperties props = new HeaderReferrerPolicyProperties();
        assertNotNull(props);
    }

    @Test void shouldSetAndGetPolicy() {
        HeaderReferrerPolicyProperties props = new HeaderReferrerPolicyProperties();
        props.setPolicy(org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER);
        assertNotNull(props.getPolicy());
    }
}
