package org.springframework.security.boot.biz.authentication.server;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.ReactiveAuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtAuthenticationWebFilterTests {

    @Test void shouldCreateInstance() {
        ReactiveAuthenticationManager manager = mock(ReactiveAuthenticationManager.class);
        JwtAuthenticationWebFilter filter = new JwtAuthenticationWebFilter(manager);
        assertNotNull(filter);
    }
}
