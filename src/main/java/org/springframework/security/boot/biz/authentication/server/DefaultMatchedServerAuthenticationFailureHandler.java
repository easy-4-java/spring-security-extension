package org.springframework.security.boot.biz.authentication.server;

import org.springframework.security.boot.biz.exception.*;
import org.springframework.security.boot.biz.utils.SubjectUtils;
import org.springframework.security.core.AuthenticationException;

/**
 * Default implementation of {@link MatchedServerAuthenticationFailureHandler} for reactive
 * (WebFlux) applications. Supports common authentication exceptions.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see MatchedServerAuthenticationFailureHandler
 */
public class DefaultMatchedServerAuthenticationFailureHandler  implements MatchedServerAuthenticationFailureHandler {

	@Override
	public boolean supports(AuthenticationException e) {
		return SubjectUtils.isAssignableFrom(e.getClass(), AuthenticationMethodNotSupportedException.class,
				AuthenticationCaptchaNotFoundException.class, AuthenticationCaptchaIncorrectException.class,
				AuthenticationTokenNotFoundException.class, AuthenticationTokenIncorrectException.class,
				AuthenticationTokenExpiredException.class);
	}

}
