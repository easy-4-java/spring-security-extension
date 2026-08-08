package org.springframework.security.boot.biz.exception;

/**
 * Authentication Token Incorrect Exception
 * @author [@Loong Wan](https://github.com/loong10k)
 */
@SuppressWarnings("serial")
/**
 * Exception thrown when an authentication token is incorrect.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see org.springframework.security.core.AuthenticationException
 */
public class AuthenticationTokenIncorrectException extends AuthenticationExceptionAdapter {

	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs an <code>AuthenticationTokenIncorrectException</code> with the specified
	 * message.
	 *
	 * @param msg the detail message
	 */
	public AuthenticationTokenIncorrectException(String msg) {
		super(AuthResponseCode.SC_AUTHZ_TOKEN_INCORRECT, msg);
	}

	/**
	 * Constructs an <code>AuthenticationTokenIncorrectException</code> with the specified
	 * message and root cause.
	 *
	 * @param msg the detail message
	 * @param t   root cause
	 */
	public AuthenticationTokenIncorrectException(String msg, Throwable t) {
		super(AuthResponseCode.SC_AUTHZ_TOKEN_INCORRECT, msg, t);
	}
}
