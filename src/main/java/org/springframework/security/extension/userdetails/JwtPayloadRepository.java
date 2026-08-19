/*
 * Copyright (c) 2018, Loong Wan (https://github.com/loong10k).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.springframework.security.extension.userdetails;

import io.github.easy4j.jwt.JwtPayload;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

/**
 * Repository abstraction for issuing, verifying and parsing JSON Web Tokens
 * within the easy-4-java security stack.
 *
 * <p>All methods on this interface are {@code default} so that implementations
 * can pick the level of indirection they want to expose. The base methods are
 * intentionally permissive &mdash; by default an empty string is returned for
 * every issue operation and {@code false} for every verify operation &mdash;
 * allowing callers to use the repository as a no-op during early integration.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see SecurityPrincipal
 * @see UserProfilePayload
 */
public interface JwtPayloadRepository {

	/**
	 * Issue a JWT for the {@link SecurityPrincipal} carried by the supplied
	 * authentication token.
	 *
	 * <p>If the token's principal is not a {@link SecurityPrincipal}, an empty
	 * string is returned. The default implementation simply delegates to
	 * {@link #issueJwt(SecurityPrincipal)}.</p>
	 *
	 * @param token the Spring Security authentication token
	 * @return the encoded JWT, or an empty string if no principal is present
	 */
	default String issueJwt(AbstractAuthenticationToken token) {
		if(token.getPrincipal() instanceof SecurityPrincipal) {
			SecurityPrincipal principal = (SecurityPrincipal) token.getPrincipal();
			return this.issueJwt(principal);
		}
		return "";
	};

	/**
	 * Issue a JWT for the supplied principal. By default delegates to
	 * {@link #issueJwt(String, Map)} using the principal's uid and profile.
	 *
	 * @param principal the security principal
	 * @return the encoded JWT
	 */
	default String issueJwt(SecurityPrincipal principal) {
		return this.issueJwt(principal.getUid(), principal.getProfile());
	};

	/**
	 * Issue a JWT for the supplied user id and profile map.
	 *
	 * <p>The default implementation returns an empty string; concrete
	 * repositories are expected to override it.</p>
	 *
	 * @param uid     the upstream user id
	 * @param profile the profile map to embed as claims
	 * @return the encoded JWT
	 */
	default String issueJwt(String uid, Map<String, Object> profile) {
		return "";
	};

	/**
	 * Verify a JWT extracted from a Spring Security authentication token.
	 *
	 * @param token        the Spring Security authentication token
	 * @param checkExpiry  whether the expiration should be checked
	 * @return {@code true} if the token is valid
	 * @throws AuthenticationException if the token cannot be verified
	 */
	default boolean verify(AbstractAuthenticationToken token, boolean checkExpiry) throws AuthenticationException{
		return false;
	};

	/**
	 * Verify a raw JWT string.
	 *
	 * @param token       the raw JWT
	 * @param checkExpiry whether the expiration should be checked
	 * @return {@code true} if the token is valid
	 * @throws AuthenticationException if the token cannot be verified
	 */
	default boolean verify(String token, boolean checkExpiry) throws AuthenticationException{
		return false;
	};

	/**
	 * Parse the JWT carried by the authentication token and return its
	 * payload.
	 *
	 * @param token       the Spring Security authentication token
	 * @param checkExpiry whether the expiration should be checked
	 * @return the parsed payload, or {@code null} if the token is invalid
	 */
	default JwtPayload getPayload(AbstractAuthenticationToken token, boolean checkExpiry){
		return null;
	};

	/**
	 * Parse a raw JWT string and return its payload.
	 *
	 * @param token       the raw JWT
	 * @param checkExpiry whether the expiration should be checked
	 * @return the parsed payload, or {@code null} if the token is invalid
	 */
	default JwtPayload getPayload(String token, boolean checkExpiry){
		return null;
	};

	/**
	 * Build a {@link UserProfilePayload} for the principal carried by the
	 * supplied authentication token and decorate it with a freshly-issued JWT.
	 *
	 * <p>The default implementation casts the token's principal to
	 * {@link SecurityPrincipal}, calls {@link SecurityPrincipal#toPayload()},
	 * embeds the freshly-issued token via {@link #issueJwt(AbstractAuthenticationToken)}
	 * and returns the resulting payload.</p>
	 *
	 * @param token       the Spring Security authentication token
	 * @param checkExpiry whether the expiration should be checked when issuing
	 * @return a profile payload populated with the freshly-issued JWT
	 */
	default UserProfilePayload getProfilePayload(AbstractAuthenticationToken token, boolean checkExpiry){

		// Reuse the authenticated principal's payload as the starting point.
		SecurityPrincipal principal = (SecurityPrincipal) token.getPrincipal();

		String tokenString = this.issueJwt(token);

		UserProfilePayload payload = principal.toPayload();
		payload.setToken(tokenString);

		return payload;

	};;

}