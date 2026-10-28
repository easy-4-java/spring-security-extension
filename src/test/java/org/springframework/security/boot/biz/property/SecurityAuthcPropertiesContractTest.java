package org.springframework.security.boot.biz.property;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityAuthcPropertiesContractTest {

    @Test
    void retainsAccessDeniedUrlFromStarter() {
        SecurityAuthcProperties properties = new SecurityAuthcProperties();
        assertEquals("/errors/401", properties.getAccessDeniedUrl());
        properties.setAccessDeniedUrl("/errors/403");
        assertEquals("/errors/403", properties.getAccessDeniedUrl());
    }
}
