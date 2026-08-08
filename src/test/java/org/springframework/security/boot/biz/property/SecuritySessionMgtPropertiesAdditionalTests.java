package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecuritySessionMgtPropertiesAdditionalTests {

    @Test void shouldSetAndGetCreationPolicy() {
        SecuritySessionMgtProperties props = new SecuritySessionMgtProperties();
        props.setCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS);
        assertEquals(org.springframework.security.config.http.SessionCreationPolicy.STATELESS, props.getCreationPolicy());
    }

    @Test void shouldSetAndGetFixationPolicy() {
        SecuritySessionMgtProperties props = new SecuritySessionMgtProperties();
        props.setFixationPolicy(SessionFixationPolicy.NEW_SESSION);
        assertEquals(SessionFixationPolicy.NEW_SESSION, props.getFixationPolicy());
    }

    @Test void shouldSetAndGetAllowSessionCreation() {
        SecuritySessionMgtProperties props = new SecuritySessionMgtProperties();
        props.setAllowSessionCreation(false);
        assertFalse(props.isAllowSessionCreation());
    }

    @Test void shouldSetAndGetSessionAttrName() {
        SecuritySessionMgtProperties props = new SecuritySessionMgtProperties();
        props.setSessionAttrName("custom");
        assertEquals("custom", props.getSessionAttrName());
    }
}
