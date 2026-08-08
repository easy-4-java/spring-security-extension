package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationProcessingFilterTests {

    // Create a concrete subclass for testing
    private static class TestAuthenticationProcessingFilter extends AuthenticationProcessingFilter {
        public TestAuthenticationProcessingFilter(String url) { super(url); }
        public TestAuthenticationProcessingFilter() { super(new AntPathRequestMatcher("/test")); }
        public org.springframework.security.core.Authentication doAttemptAuthentication(
                jakarta.servlet.http.HttpServletRequest request,
                jakarta.servlet.http.HttpServletResponse response) { return null; }
    }

    @Test void shouldHaveDefaultConstants() {
        assertEquals("0.000000", AuthenticationProcessingFilter.DEFAULT_LONGITUDE_LATITUDE);
        assertEquals("X-Uid", AuthenticationProcessingFilter.UID_HEADER);
        assertEquals("X-Sign", AuthenticationProcessingFilter.SIGN_HEADER);
        assertEquals("X-Longitude", AuthenticationProcessingFilter.LONGITUDE_HEADER);
        assertEquals("X-Latitude", AuthenticationProcessingFilter.LATITUDE_HEADER);
        assertEquals("X-APP-ID", AuthenticationProcessingFilter.APP_ID_HEADER);
        assertEquals("X-APP-CHANNEL", AuthenticationProcessingFilter.APP_CHANNEL_HEADER);
        assertEquals("X-APP-VERSION", AuthenticationProcessingFilter.APP_VERSION_HEADER);
    }

    @Test void shouldCreateWithUrl() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter("/login");
        assertNotNull(filter);
    }

    @Test void shouldCreateWithMatcher() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        assertNotNull(filter);
    }

    @Test void shouldSetAndGetUidHeaderName() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        filter.setUidHeaderName("X-User-Id");
        assertEquals("X-User-Id", filter.getUidHeaderName());
    }

    @Test void shouldSetAndGetSignHeaderName() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        filter.setSignHeaderName("X-Token");
        assertEquals("X-Token", filter.getSignHeaderName());
    }

    @Test void shouldSetAndGetLongitudeHeaderName() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        filter.setLongitudeHeaderName("X-Lon");
        assertEquals("X-Lon", filter.getLongitudeHeaderName());
    }

    @Test void shouldSetAndGetLatitudeHeaderName() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        filter.setLatitudeHeaderName("X-Lat");
        assertEquals("X-Lat", filter.getLatitudeHeaderName());
    }

    @Test void shouldSetAndGetAppIdHeaderName() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        filter.setAppIdHeaderName("X-Application-Id");
        assertEquals("X-Application-Id", filter.getAppIdHeaderName());
    }

    @Test void shouldSetAndGetAppChannelHeaderName() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        filter.setAppChannelHeaderName("X-Channel");
        assertEquals("X-Channel", filter.getAppChannelHeaderName());
    }

    @Test void shouldSetAndGetAppVersionHeaderName() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        filter.setAppVersionHeaderName("X-Version");
        assertEquals("X-Version", filter.getAppVersionHeaderName());
    }

    @Test void shouldHaveDefaultHeaderNames() {
        TestAuthenticationProcessingFilter filter = new TestAuthenticationProcessingFilter();
        assertEquals("X-Uid", filter.getUidHeaderName());
        assertEquals("X-Sign", filter.getSignHeaderName());
        assertEquals("X-Longitude", filter.getLongitudeHeaderName());
        assertEquals("X-Latitude", filter.getLatitudeHeaderName());
        assertEquals("X-APP-ID", filter.getAppIdHeaderName());
        assertEquals("X-APP-CHANNEL", filter.getAppChannelHeaderName());
        assertEquals("X-APP-VERSION", filter.getAppVersionHeaderName());
    }
}
