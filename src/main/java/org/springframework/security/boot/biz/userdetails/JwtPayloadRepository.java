package org.springframework.security.boot.biz.userdetails;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

/**
 * Abstract JSON Web Token (JWT) Payload Repository
 */
public interface JwtPayloadRepository {

    default String issueJwt(AbstractAuthenticationToken token) { 
        if(token.getPrincipal() instanceof SecurityPrincipal) {
            SecurityPrincipal principal = (SecurityPrincipal) token.getPrincipal();
            return this.issueJwt(principal);
        }
        return "";
    }
    
    default String issueJwt(SecurityPrincipal principal) { 
        return this.issueJwt(principal.getUid(), principal.getProfile());
    }
    
    default String issueJwt(String uid, Map<String, Object> profile) { 
        return "";
    }

    default boolean verify(AbstractAuthenticationToken token, boolean checkExpiry) throws AuthenticationException{
        return false;
    }
    
    default boolean verify(String token, boolean checkExpiry) throws AuthenticationException{
        return false;
    }

    default Object getPayload(AbstractAuthenticationToken token, boolean checkExpiry){
        return null;
    }
    
    default Object getPayload(String token, boolean checkExpiry){
        return null;
    }
    
    default UserProfilePayload getProfilePayload(AbstractAuthenticationToken token, boolean checkExpiry){
        SecurityPrincipal principal = (SecurityPrincipal) token.getPrincipal();
        String tokenString = this.issueJwt(token);
        UserProfilePayload payload = principal.toPayload();
        payload.setToken(tokenString);
        return payload;
    };
    
}
