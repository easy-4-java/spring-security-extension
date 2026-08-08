package org.springframework.security.boot.biz.exception;

/**
 * Authentication Method Not Supported Exception
 * @author [@Loong Wan](https://github.com/loong10k)
 */
@SuppressWarnings("serial")
/**
 * Exception thrown when an unsupported HTTP method is used for authentication.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see org.springframework.security.core.AuthenticationException
 */
public class AuthenticationMethodNotSupportedException extends AuthenticationExceptionAdapter {
	
	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs an <code>AuthenticationMethodNotSupportedException</code> with the
	 * specified message.
	 *
	 * @param msg the detail message
	 */
	public AuthenticationMethodNotSupportedException(String msg) {
		super(AuthResponseCode.SC_AUTHC_METHOD_NOT_ALLOWED, msg);
	}

	/**
	 * Constructs an <code>AuthenticationMethodNotSupportedException</code> with the
	 * specified message and root cause.
	 *
	 * @param msg the detail message
	 * @param t root cause
	 */
	public AuthenticationMethodNotSupportedException(String msg, Throwable t) {
		super(AuthResponseCode.SC_AUTHC_METHOD_NOT_ALLOWED, msg, t);
	}
	
}
