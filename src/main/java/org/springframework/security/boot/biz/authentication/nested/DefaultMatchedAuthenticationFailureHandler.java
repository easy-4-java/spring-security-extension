package org.springframework.security.boot.biz.authentication.nested;

import org.springframework.security.boot.biz.exception.*;
import org.springframework.security.boot.biz.utils.SubjectUtils;
import org.springframework.security.core.AuthenticationException;

/**
 * Default implementation of {@link MatchedAuthenticationFailureHandler} that supports
 * common authentication exceptions such as method-not-supported, captcha errors,
 * and token-related errors.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see MatchedAuthenticationFailureHandler
 */
public class DefaultMatchedAuthenticationFailureHandler  implements MatchedAuthenticationFailureHandler {

	@Override
	public boolean supports(AuthenticationException e) {
		return SubjectUtils.isAssignableFrom(e.getClass(), AuthenticationMethodNotSupportedException.class,
				AuthenticationCaptchaNotFoundException.class, AuthenticationCaptchaIncorrectException.class,
				AuthenticationTokenNotFoundException.class, AuthenticationTokenIncorrectException.class,
				AuthenticationTokenExpiredException.class);
	}

}
