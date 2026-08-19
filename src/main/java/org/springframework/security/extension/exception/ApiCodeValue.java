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
 * Centralised registry of integer API codes used by the authentication and
 * authorisation layers of the easy-4-java security stack.
 *
 * <p>The constants follow the convention {@code 100xx} for authentication
 * failures, {@code 1002x} for authorisation/token failures and
 * {@code 10030} for third-party authorisation server failures. Each value is
 * referenced by a corresponding {@link AuthResponseCode} constant.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see AuthResponseCode
 * @see AuthResponse
 */
public final class ApiCodeValue {

	/** API code {@code 200} - success. */
	public final static int SC_SUCCESS = 200;
	/** API code {@code 1000} - generic failure. */
	public final static int SC_FAIL = 1000;

	/** API code {@code 10001} - account login failed. */
	public final static int SC_AUTHC_FAIL = 10001;
	/** API code {@code 10002} - authentication request method not supported. */
	public final static int SC_AUTHC_METHOD_NOT_ALLOWED = 10002;
	/** API code {@code 10003} - the login retry limit has been exceeded. */
	public final static int SC_AUTHC_OVER_RETRY_REMIND = 10003;
	/** API code {@code 10004} - captcha missing. */
	public final static int SC_AUTHC_CAPTCHA_REQUIRED = 10004;
	/** API code {@code 10005} - captcha has expired. */
	public final static int SC_AUTHC_CAPTCHA_EXPIRED = 10005;
	/** API code {@code 10006} - captcha is incorrect. */
	public final static int SC_AUTHC_CAPTCHA_INCORRECT = 10006;
	/** API code {@code 10007} - the user account does not exist. */
	public final static int SC_AUTHC_ACCOUNT_NOT_FOUND = 10007;
	/** API code {@code 10008} - the user account is disabled. */
	public final static int SC_AUTHC_ACCOUNT_DISABLED = 10008;
	/** API code {@code 10009} - the user account has expired. */
	public final static int SC_AUTHC_ACCOUNT_EXPIRED = 10009;
	/** API code {@code 10010} - the user account is locked. */
	public final static int SC_AUTHC_ACCOUNT_LOCKED = 10010;
	/** API code {@code 10011} - the user credentials have expired. */
	public final static int SC_AUTHC_CREDENTIALS_EXPIRED = 10011;
	/** API code {@code 10012} - the username or password is incorrect. */
	public final static int SC_AUTHC_BAD_CREDENTIALS = 10012;
	/** API code {@code 10020} - function authorisation failed. */
	public final static int SC_AUTHZ_FAIL = 10020;
	/** API code {@code 10021} - the token could not be issued. */
	public final static int SC_AUTHZ_TOKEN_ISSUED = 10021;
	/** API code {@code 10022} - the token is missing. */
	public final static int SC_AUTHZ_TOKEN_REQUIRED = 10022;
	/** API code {@code 10023} - the token has expired. */
	public final static int SC_AUTHZ_TOKEN_EXPIRED = 10023;
	/** API code {@code 10024} - the token has been invalidated. */
	public final static int SC_AUTHZ_TOKEN_INVALID = 10024;
	/** API code {@code 10025} - the token is incorrect. */
	public final static int SC_AUTHZ_TOKEN_INCORRECT = 10025;
	/** API code {@code 10026} - the temporary authorisation code is missing. */
	public final static int SC_AUTHZ_CODE_REQUIRED = 10026;
	/** API code {@code 10027} - the temporary authorisation code has expired. */
	public final static int SC_AUTHZ_CODE_EXPIRED = 10027;
	/** API code {@code 10028} - the temporary authorisation code has been invalidated. */
	public final static int SC_AUTHZ_CODE_INVALID = 10028;
	/** API code {@code 10029} - the temporary authorisation code is incorrect. */
	public final static int SC_AUTHZ_CODE_INCORRECT = 10029;
	/** API code {@code 10030} - the third-party authorisation server is unavailable. */
	public final static int SC_AUTHZ_THIRD_PARTY_SERVICE = 10030;

}