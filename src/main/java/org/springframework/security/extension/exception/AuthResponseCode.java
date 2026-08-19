/*
 * Copyright (c) 2018-present, easy-4-java (https://github.com/easy-4-java).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.extension.exception;

/**
 * Enumeration of response codes used by the authentication and authorisation
 * layers of the easy-4-java security stack.
 *
 * <p>Every constant combines three pieces of information:</p>
 * <ul>
 *     <li>an integer {@link #getCode() code} that follows the API code
 *         convention defined in {@link ApiCodeValue};</li>
 *     <li>a {@link #getStatus() status} string drawn from
 *         {@link AuthConstants} ({@code success}, {@code fail} or
 *         {@code error});</li>
 *     <li>a {@link #getMsgKey() message key} that the caller can resolve
 *         against an internationalised resource bundle.</li>
 * </ul>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see ApiCodeValue
 * @see AuthConstants
 * @see AuthResponse
 */
public enum AuthResponseCode {

	/**
	 * Authentication succeeded.
	 */
	SC_AUTHC_SUCCESS(ApiCodeValue.SC_SUCCESS, AuthConstants.RT_SUCCESS, "spring.security.authc.success"),

	/**
	 * Authentication failed.
	 */
	SC_AUTHC_FAIL(ApiCodeValue.SC_AUTHC_FAIL, AuthConstants.RT_ERROR, "spring.security.authc.fail"),

	/**
	 * The HTTP method used for authentication is not supported.
	 */
	SC_AUTHC_METHOD_NOT_ALLOWED(ApiCodeValue.SC_AUTHC_METHOD_NOT_ALLOWED, AuthConstants.RT_ERROR,
			"spring.security.authc.method-not-supported"),
	/**
	 * The number of login attempts exceeded the maximum retry limit and a
	 * verification code is now required.
	 */
	SC_AUTHC_OVER_RETRY_REMIND(ApiCodeValue.SC_AUTHC_OVER_RETRY_REMIND, AuthConstants.RT_ERROR,
			"spring.security.authc.over-retry-remind"),
	/**
	 * The captcha parameter was not supplied.
	 */
	SC_AUTHC_CAPTCHA_REQUIRED(ApiCodeValue.SC_AUTHC_CAPTCHA_REQUIRED, AuthConstants.RT_ERROR,
			"spring.security.authc.captcha.required"),
	/**
	 * The supplied captcha has expired.
	 */
	SC_AUTHC_CAPTCHA_EXPIRED(ApiCodeValue.SC_AUTHC_CAPTCHA_EXPIRED, AuthConstants.RT_ERROR,
			"spring.security.authc.captcha.expired"),
	/**
	 * The supplied captcha is incorrect.
	 */
	SC_AUTHC_CAPTCHA_INCORRECT(ApiCodeValue.SC_AUTHC_CAPTCHA_INCORRECT, AuthConstants.RT_ERROR,
			"spring.security.authc.captcha.incorrect"),

	/**
	 * The user account does not exist.
	 */
	SC_AUTHC_ACCOUNT_NOT_FOUND(ApiCodeValue.SC_AUTHC_ACCOUNT_NOT_FOUND, AuthConstants.RT_ERROR,
			"spring.security.authc.principal.not-found"),
	/**
	 * The user account is disabled.
	 */
	SC_AUTHC_ACCOUNT_DISABLED(ApiCodeValue.SC_AUTHC_ACCOUNT_DISABLED, AuthConstants.RT_ERROR,
			"spring.security.authc.principal.disabled"),
	/**
	 * The user account has expired.
	 */
	SC_AUTHC_ACCOUNT_EXPIRED(ApiCodeValue.SC_AUTHC_ACCOUNT_EXPIRED, AuthConstants.RT_ERROR,
			"spring.security.authc.principal.expired"),
	/**
	 * The user account is locked.
	 */
	SC_AUTHC_ACCOUNT_LOCKED(ApiCodeValue.SC_AUTHC_ACCOUNT_LOCKED, AuthConstants.RT_ERROR,
			"spring.security.authc.principal.locked"),

	/**
	 * The user credentials have expired.
	 */
	SC_AUTHC_CREDENTIALS_EXPIRED(ApiCodeValue.SC_AUTHC_CREDENTIALS_EXPIRED, AuthConstants.RT_ERROR,
			"spring.security.authc.credentials.expired"),
	/**
	 * The supplied credentials are invalid.
	 */
	SC_AUTHC_BAD_CREDENTIALS(ApiCodeValue.SC_AUTHC_BAD_CREDENTIALS, AuthConstants.RT_ERROR,
			"spring.security.authc.credentials.incorrect"),

	/**
	 * Authorisation succeeded.
	 */
	SC_AUTHZ_SUCCESS(ApiCodeValue.SC_SUCCESS, AuthConstants.RT_SUCCESS, "spring.security.authz.success"),
	/**
	 * Authorisation failed.
	 */
	SC_AUTHZ_FAIL(ApiCodeValue.SC_AUTHZ_FAIL, AuthConstants.RT_ERROR, "spring.security.authz.fail"),
	/**
	 * The token could not be issued.
	 */
	SC_AUTHZ_TOKEN_ISSUED(ApiCodeValue.SC_AUTHZ_TOKEN_ISSUED, AuthConstants.RT_ERROR,
			"spring.security.authz.token.issued"),
	/**
	 * The token parameter was not provided.
	 */
	SC_AUTHZ_TOKEN_REQUIRED(ApiCodeValue.SC_AUTHZ_TOKEN_REQUIRED, AuthConstants.RT_ERROR,
			"spring.security.authz.token.required"),
	/**
	 * The supplied token has expired.
	 */
	SC_AUTHZ_TOKEN_EXPIRED(ApiCodeValue.SC_AUTHZ_TOKEN_EXPIRED, AuthConstants.RT_ERROR,
			"spring.security.authz.token.expired"),
	/**
	 * The supplied token is no longer valid.
	 */
	SC_AUTHZ_TOKEN_INVALID(ApiCodeValue.SC_AUTHZ_TOKEN_INVALID, AuthConstants.RT_ERROR,
			"spring.security.authz.token.invalid"),
	/**
	 * The supplied token is incorrect.
	 */
	SC_AUTHZ_TOKEN_INCORRECT(ApiCodeValue.SC_AUTHZ_TOKEN_INCORRECT, AuthConstants.RT_ERROR,
			"spring.security.authz.token.incorrect"),
	/**
	 * The authorisation code was not supplied.
	 */
	SC_AUTHZ_CODE_REQUIRED(ApiCodeValue.SC_AUTHZ_CODE_REQUIRED, AuthConstants.RT_ERROR,
			"spring.security.authz.code.required"),
	/**
	 * The authorisation code has expired.
	 */
	SC_AUTHZ_CODE_EXPIRED(ApiCodeValue.SC_AUTHZ_CODE_EXPIRED, AuthConstants.RT_ERROR,
			"spring.security.authz.code.expired"),
	/**
	 * The authorisation code is no longer valid.
	 */
	SC_AUTHZ_CODE_INVALID(ApiCodeValue.SC_AUTHZ_CODE_INVALID, AuthConstants.RT_ERROR,
			"spring.security.authz.code.invalid"),
	/**
	 * The authorisation code is incorrect.
	 */
	SC_AUTHZ_CODE_INCORRECT(ApiCodeValue.SC_AUTHZ_CODE_INCORRECT, AuthConstants.RT_ERROR,
			"spring.security.authz.code.incorrect"),
	/**
	 * The third-party authorisation server returned an error.
	 */
	SC_AUTHZ_THIRD_PARTY_SERVICE(ApiCodeValue.SC_AUTHZ_THIRD_PARTY_SERVICE, AuthConstants.RT_ERROR,
			"spring.security.authz.server.error");

	private final int code;
	private final String status;
	private final String msgKey;

	private AuthResponseCode(int code, String status, String msgKey) {
		this.code = code;
		this.status = status;
		this.msgKey = msgKey;
	}

	/**
	 * @return the integer API code associated with this response
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the coarse-grained status string ({@code success},
	 *         {@code fail} or {@code error})
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the i18n message key for this response
	 */
	public String getMsgKey() {
		return msgKey;
	}
}