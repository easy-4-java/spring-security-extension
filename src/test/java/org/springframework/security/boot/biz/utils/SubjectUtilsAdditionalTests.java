package org.springframework.security.boot.biz.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.boot.biz.userdetails.SecurityPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SubjectUtilsAdditionalTests {

    @Test void shouldGetRequestAttributes() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(req, new MockHttpServletResponse()));
        assertNotNull(SubjectUtils.getRequestAttributes());
        RequestContextHolder.resetRequestAttributes();
    }

    @Test void shouldGetRequest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(req, new MockHttpServletResponse()));
        assertNotNull(SubjectUtils.getRequest());
        RequestContextHolder.resetRequestAttributes();
    }

    @Test void shouldGetResponse() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletWebRequest(req, res));
        assertNotNull(SubjectUtils.getResponse());
        RequestContextHolder.resetRequestAttributes();
    }

    @Test void shouldGetSession() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(req, new MockHttpServletResponse()));
        assertNotNull(SubjectUtils.getSession(true));
        RequestContextHolder.resetRequestAttributes();
    }

    @Test void shouldCopySession() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(req, res));
        javax.servlet.http.HttpSession oldSession = req.getSession();
        oldSession.setAttribute("key1", "value1");
        oldSession.setAttribute("key2", "value2");
        javax.servlet.http.HttpSession newSession = SubjectUtils.copySession(req, oldSession);
        assertNotNull(newSession);
        assertEquals("value1", newSession.getAttribute("key1"));
        assertEquals("value2", newSession.getAttribute("key2"));
        RequestContextHolder.resetRequestAttributes();
    }

    @Test void shouldGetUserIdWhenAuthenticated() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        principal.setUid("uid1");
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList()));
        assertEquals("uid1", SubjectUtils.getUserId());
        SecurityContextHolder.clearContext();
    }

    @Test void shouldReturnNullUserIdWhenNotAuthenticated() {
        SecurityContextHolder.clearContext();
        assertNull(SubjectUtils.getUserId());
    }

    @Test void shouldGetUserIdLong() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        principal.setUid("123");
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList()));
        assertEquals(123L, SubjectUtils.getUserIdLong());
        SecurityContextHolder.clearContext();
    }

    @Test void shouldReturnNullUserIdLongWhenNotAuthenticated() {
        SecurityContextHolder.clearContext();
        assertNull(SubjectUtils.getUserIdLong());
    }

    @Test void shouldGetProfileString() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("city", "Shanghai");
        principal.setProfile(profile);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList()));
        assertEquals("Shanghai", SubjectUtils.getProfileString("city"));
        assertEquals("default", SubjectUtils.getProfileString("missing", "default"));
        SecurityContextHolder.clearContext();
    }

    @Test void shouldReturnNullProfileStringWhenNotAuthenticated() {
        SecurityContextHolder.clearContext();
        assertNull(SubjectUtils.getProfileString("key"));
    }

    @Test void shouldGetProfileStringWithAuth() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("city", "Beijing");
        principal.setProfile(profile);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList());
        assertEquals("Beijing", SubjectUtils.getProfileString(auth, "city"));
        assertEquals("default", SubjectUtils.getProfileString(auth, "missing", "default"));
    }

    @Test void shouldReturnNullProfileStringWithNullAuth() {
        assertNull(SubjectUtils.getProfileString((org.springframework.security.core.Authentication) null, "key"));
    }

    @Test void shouldGetProfileInteger() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("age", 25);
        principal.setProfile(profile);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList()));
        assertEquals(25, SubjectUtils.getProfileInteger("age"));
        assertEquals(Integer.valueOf(99), SubjectUtils.getProfileInteger("missing", 99));
        SecurityContextHolder.clearContext();
    }

    @Test void shouldGetProfileIntegerWithAuth() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("age", 30);
        principal.setProfile(profile);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList());
        assertEquals(30, SubjectUtils.getProfileInteger(auth, "age"));
        assertEquals(Integer.valueOf(99), SubjectUtils.getProfileInteger(auth, "missing", 99));
    }

    @Test void shouldGetProfileLong() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("id", 12345L);
        principal.setProfile(profile);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList()));
        assertEquals(12345L, SubjectUtils.getProfileLong("id"));
        assertEquals(Long.valueOf(99L), SubjectUtils.getProfileLong("missing", 99L));
        SecurityContextHolder.clearContext();
    }

    @Test void shouldGetProfileLongWithAuth() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("id", 67890L);
        principal.setProfile(profile);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList());
        assertEquals(67890L, SubjectUtils.getProfileLong(auth, "id"));
        assertEquals(Long.valueOf(99L), SubjectUtils.getProfileLong(auth, "missing", 99L));
    }

    @Test void shouldGetProfileDouble() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("score", 9.5);
        principal.setProfile(profile);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList()));
        assertEquals(9.5, SubjectUtils.getProfileDouble("score"));
        assertEquals(Double.valueOf(0.0), SubjectUtils.getProfileDouble("missing", 0.0));
        SecurityContextHolder.clearContext();
    }

    @Test void shouldGetProfileDoubleWithAuth() {
        SecurityPrincipal principal = new SecurityPrincipal("user", "pass", "ROLE_USER");
        Map<String, Object> profile = new HashMap<>();
        profile.put("score", 8.5);
        principal.setProfile(profile);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, "pass", Collections.emptyList());
        assertEquals(8.5, SubjectUtils.getProfileDouble(auth, "score"));
        assertEquals(Double.valueOf(0.0), SubjectUtils.getProfileDouble(auth, "missing", 0.0));
    }

    @Test void shouldGetProfileTypesWhenNotAuthenticated() {
        SecurityContextHolder.clearContext();
        assertNull(SubjectUtils.getProfileInteger("key"));
        assertNull(SubjectUtils.getProfileLong("key"));
        assertNull(SubjectUtils.getProfileDouble("key"));
    }

    @Test void shouldHandleToLongFunction() {
        assertEquals(123L, SubjectUtils.TO_LONG.apply(123));
        assertEquals(456L, SubjectUtils.TO_LONG.apply("456"));
        assertNull(SubjectUtils.TO_LONG.apply(null));
    }
}
