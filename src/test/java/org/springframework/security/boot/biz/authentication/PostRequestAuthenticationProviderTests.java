package org.springframework.security.boot.biz.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.boot.biz.userdetails.SecurityPrincipal;
import org.springframework.security.boot.biz.userdetails.UserDetailsServiceAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PostRequestAuthenticationProviderTests {

    @Test void shouldSupportUsernamePasswordToken() {
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetailsWithoutPwd(String username) {
                return null;
            }
        };
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, encoder);
        assertTrue(provider.supports(UsernamePasswordAuthenticationToken.class));
    }

    @Test void shouldNotSupportOtherTokenTypes() {
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetailsWithoutPwd(String username) {
                return null;
            }
        };
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, encoder);
        assertFalse(provider.supports(String.class));
    }

    @Test void shouldThrowWhenAuthenticationIsNull() {
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetailsWithoutPwd(String username) { return null; }
        };
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, new BCryptPasswordEncoder());
        assertThrows(IllegalArgumentException.class, () -> provider.authenticate(null));
    }

    @Test void shouldThrowWhenUsernameIsEmpty() {
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetailsWithoutPwd(String username) { return null; }
        };
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, new BCryptPasswordEncoder());
        Authentication auth = new UsernamePasswordAuthenticationToken("", "pass");
        assertThrows(BadCredentialsException.class, () -> provider.authenticate(auth));
    }

    @Test void shouldThrowWhenPasswordIsEmpty() {
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetailsWithoutPwd(String username) { return null; }
        };
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, new BCryptPasswordEncoder());
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "");
        assertThrows(BadCredentialsException.class, () -> provider.authenticate(auth));
    }

    @Test void shouldAuthenticateSuccessfully() {
        String encodedPw = new BCryptPasswordEncoder().encode("pass");
        SecurityPrincipal principal = new SecurityPrincipal("user", encodedPw, "ROLE_USER");
        principal.setUid("uid1");
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetails(Authentication token) {
                return principal;
            }
            public UserDetails loadUserDetailsWithoutPwd(String username) { return principal; }
        };
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, new BCryptPasswordEncoder());
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        Authentication result = provider.authenticate(auth);
        assertNotNull(result);
        assertTrue(result.isAuthenticated());
    }

    @Test void shouldThrowWhenPasswordDoesNotMatch() {
        String encodedPw = new BCryptPasswordEncoder().encode("correct");
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetails(Authentication token) {
                return new org.springframework.security.core.userdetails.User("user", encodedPw, Collections.emptyList());
            }
            public UserDetails loadUserDetailsWithoutPwd(String username) {
                return new org.springframework.security.core.userdetails.User("user", encodedPw, Collections.emptyList());
            }
        };
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, new BCryptPasswordEncoder());
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "wrong");
        assertThrows(BadCredentialsException.class, () -> provider.authenticate(auth));
    }

    @Test void shouldReturnPasswordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetailsWithoutPwd(String username) { return null; }
        };
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, encoder);
        assertSame(encoder, provider.getPasswordEncoder());
    }

    @Test void shouldReturnUserDetailsService() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetailsServiceAdapter uds = new UserDetailsServiceAdapter() {
            public UserDetails loadUserDetailsWithoutPwd(String username) { return null; }
        };
        PostRequestAuthenticationProvider provider = new PostRequestAuthenticationProvider(uds, encoder);
        assertSame(uds, provider.getUserDetailsService());
    }
}
