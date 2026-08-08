package org.springframework.security.boot.biz.userdetails;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsServiceAdapterTests {

    @Test void shouldLoadUserDetailsFromToken() {
        UserDetailsServiceAdapter adapter = new UserDetailsServiceAdapter() {
            public org.springframework.security.core.userdetails.UserDetails loadUserDetailsWithoutPwd(String username) {
                return new org.springframework.security.core.userdetails.User(username, "pass",
                        java.util.Collections.emptyList());
            }
        };
        Authentication auth = new UsernamePasswordAuthenticationToken("testuser", "pass");
        var details = adapter.loadUserDetails(auth);
        assertNotNull(details);
        assertEquals("testuser", details.getUsername());
    }

    @Test void shouldReturnNullWhenPrincipalIsNotString() {
        UserDetailsServiceAdapter adapter = new UserDetailsServiceAdapter() {
            public org.springframework.security.core.userdetails.UserDetails loadUserDetailsWithoutPwd(String username) {
                return null;
            }
        };
        Authentication auth = new UsernamePasswordAuthenticationToken(123, "pass");
        assertNull(adapter.loadUserDetails(auth));
    }

    @Test void shouldReturnNullForLoadUserDetailsByUserId() {
        UserDetailsServiceAdapter adapter = new UserDetailsServiceAdapter() {
            public org.springframework.security.core.userdetails.UserDetails loadUserDetailsWithoutPwd(String username) {
                return null;
            }
        };
        assertNull(adapter.loadUserDetails("uid"));
    }

    @Test void shouldReturnNullForLoadUserDetailsByUserIdAndRoleId() {
        UserDetailsServiceAdapter adapter = new UserDetailsServiceAdapter() {
            public org.springframework.security.core.userdetails.UserDetails loadUserDetailsWithoutPwd(String username) {
                return null;
            }
        };
        assertNull(adapter.loadUserDetails("uid", "rid"));
    }

    @Test void shouldReturnNullForUpdatePassword() {
        UserDetailsServiceAdapter adapter = new UserDetailsServiceAdapter() {
            public org.springframework.security.core.userdetails.UserDetails loadUserDetailsWithoutPwd(String username) {
                return null;
            }
        };
        assertNull(adapter.updatePassword(null, "newPass"));
    }

    @Test void shouldDelegateLoadByUsernameToLoadWithoutPwd() {
        UserDetailsServiceAdapter adapter = new UserDetailsServiceAdapter() {
            public org.springframework.security.core.userdetails.UserDetails loadUserDetailsWithoutPwd(String username) {
                return new org.springframework.security.core.userdetails.User(username, "", java.util.Collections.emptyList());
            }
        };
        var details = adapter.loadUserByUsername("test");
        assertNotNull(details);
        assertEquals("test", details.getUsername());
    }
}
