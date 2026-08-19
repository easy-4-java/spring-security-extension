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

import java.util.HashMap;
import java.util.Map;

/**
 * Generic response envelope returned by the authentication and authorisation
 * layers to API clients.
 *
 * <p>Each instance is an immutable quadruple of {@code (code, status, message,
 * data)} with a settable {@code data} payload for late-binding scenarios. The
 * accompanying static factory methods ({@link #success success},
 * {@link #fail fail}, {@link #of of}) provide a fluent way to build typical
 * responses and convert the envelope into a {@link Map} via
 * {@link #toMap()} for direct serialisation.</p>
 *
 * @param <T> the type of the {@code data} payload
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see AuthResponseCode
 * @see AuthConstants
 */
public class AuthResponse<T> {

	/** Success or failure code (see {@link ApiCodeValue}). */
	private final int code;
	/** Coarse-grained status: {@code success}, {@code fail} or {@code error}. */
	private final String status;
	/** Success or failure message. */
	private final String message;
	/** Success or failure data payload. */
	private T data;

	/**
	 * Build a successful response carrying only a message.
	 *
	 * @param message the human-readable message
	 */
	public AuthResponse(final String message) {
		this.code = AuthResponseCode.SC_AUTHC_SUCCESS.getCode();
		this.status = AuthConstants.RT_SUCCESS;
		this.message = message;
	}

	/**
	 * Build a response that mirrors the supplied {@link AuthResponseCode}.
	 *
	 * @param code the response code, providing the integer code and status
	 */
	protected AuthResponse(final AuthResponseCode code) {
		this.code = code.getCode();
		;
		this.status = code.getStatus();
		this.message = null;
	}

	/**
	 * Build a response that mirrors the supplied {@link AuthResponseCode} and
	 * carries a data payload.
	 *
	 * @param code the response code, providing the integer code and status
	 * @param data the data payload to attach
	 */
	protected AuthResponse(final AuthResponseCode code, final T data) {
		this.code = code.getCode();
		;
		this.status = code.getStatus();
		this.message = null;
		this.data = data;
	}

	/**
	 * Build a response that mirrors the supplied {@link AuthResponseCode} and
	 * carries a custom message and data payload.
	 *
	 * @param code    the response code, providing the integer code and status
	 * @param message the human-readable message
	 * @param data    the data payload to attach
	 */
	protected AuthResponse(final AuthResponseCode code, final String message, final T data) {
		this.code = code.getCode();
		;
		this.status = code.getStatus();
		this.message = message;
		this.data = data;
	}

	/**
	 * Build a response that uses an arbitrary integer code and a message;
	 * the status defaults to {@link AuthConstants#RT_SUCCESS}.
	 *
	 * @param code    the integer code
	 * @param message the human-readable message
	 */
	protected AuthResponse(final int code, final String message) {
		this(code, AuthConstants.RT_SUCCESS, message);
	}

	/**
	 * Build a response that uses an arbitrary integer code, status and
	 * message (data defaults to {@code null}).
	 *
	 * @param code    the integer code
	 * @param status  the coarse-grained status string
	 * @param message the human-readable message
	 */
	protected AuthResponse(final int code, final String status, final String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	/**
	 * Build a response that uses an arbitrary integer code and message; the
	 * status defaults to {@link AuthConstants#RT_SUCCESS} (data is supplied).
	 *
	 * @param code    the integer code
	 * @param message the human-readable message
	 * @param data    the data payload to attach
	 */
	protected AuthResponse(final int code, final String message, final T data) {
		this(code, AuthConstants.RT_SUCCESS, message, data);
	}

	/**
	 * Build a fully customisable response.
	 *
	 * @param code    the integer code
	 * @param status  the coarse-grained status string
	 * @param message the human-readable message
	 * @param data    the data payload to attach
	 */
	protected AuthResponse(final int code, final String status, final String message, final T data) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.data = data;
	}

	// success -----------------------------------------------------------------

	/**
	 * Build a successful response carrying only a message.
	 *
	 * @param <T>     the data type
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse} that uses
	 *         {@link AuthResponseCode#SC_AUTHC_SUCCESS}
	 */
	public static <T> AuthResponse<T> success(final String message) {
		return of(AuthResponseCode.SC_AUTHC_SUCCESS, message, null);
	}

	/**
	 * Build a successful response carrying only a data payload.
	 *
	 * @param <T>  the data type
	 * @param data the data payload to attach
	 * @return a fresh {@link AuthResponse} that uses
	 *         {@link AuthResponseCode#SC_AUTHC_SUCCESS}
	 */
	public static <T> AuthResponse<T> success(final T data) {
		return of(AuthResponseCode.SC_AUTHC_SUCCESS, data);
	}

	/**
	 * Build a successful response carrying both a message and a data payload.
	 *
	 * @param <T>     the data type
	 * @param message the human-readable message
	 * @param data    the data payload to attach
	 * @return a fresh {@link AuthResponse} that uses
	 *         {@link AuthResponseCode#SC_AUTHC_SUCCESS}
	 */
	public static <T> AuthResponse<T> success(final String message, final T data) {
		return of(AuthResponseCode.SC_AUTHC_SUCCESS, message, data);
	}

	/**
	 * Build a successful response with a custom integer code.
	 *
	 * @param <T>     the data type
	 * @param code    the integer code
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse} with status
	 *         {@link AuthConstants#RT_SUCCESS}
	 */
	public static <T> AuthResponse<T> success(final int code, final String message) {
		return of(code, AuthConstants.RT_SUCCESS, message);
	}

	// fail -----------------------------------------------------------------

	/**
	 * Build a failed response carrying only a message.
	 *
	 * @param <T>     the data type
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse} that uses
	 *         {@link AuthResponseCode#SC_AUTHC_FAIL}
	 */
	public static <T> AuthResponse<T> fail(final String message) {
		return of(AuthResponseCode.SC_AUTHC_FAIL, message, null);
	}

	/**
	 * Build a failed response carrying only a data payload.
	 *
	 * @param <T>  the data type
	 * @param data the data payload to attach
	 * @return a fresh {@link AuthResponse} that uses
	 *         {@link AuthResponseCode#SC_AUTHC_FAIL}
	 */
	public static <T> AuthResponse<T> fail(final T data) {
		return of(AuthResponseCode.SC_AUTHC_FAIL, data);
	}

	/**
	 * Build a failed response with a custom integer code.
	 *
	 * @param <T>     the data type
	 * @param code    the integer code
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse} with status
	 *         {@link AuthConstants#RT_FAIL}
	 */
	public static <T> AuthResponse<T> fail(final int code, final String message) {
		return of(code, AuthConstants.RT_FAIL, message);
	}

	// -----------------------------------------------------------------

	/**
	 * Build a response that mirrors the supplied {@link AuthResponseCode} and
	 * carries no extra data.
	 *
	 * @param <T>  the data type
	 * @param code the response code
	 * @return a fresh {@link AuthResponse}
	 */
	public static <T> AuthResponse<T> of(final AuthResponseCode code) {
		return new AuthResponse<T>(code);
	}

	/**
	 * Build a response that mirrors the supplied {@link AuthResponseCode} and
	 * carries a data payload.
	 *
	 * @param <T>  the data type
	 * @param code the response code
	 * @param data the data payload to attach
	 * @return a fresh {@link AuthResponse}
	 */
	public static <T> AuthResponse<T> of(final AuthResponseCode code, final T data) {
		return new AuthResponse<T>(code, data);
	}

	/**
	 * Build a response that mirrors the supplied {@link AuthResponseCode} and
	 * carries a custom message and data payload.
	 *
	 * @param <T>     the data type
	 * @param code    the response code
	 * @param message the human-readable message
	 * @param data    the data payload to attach
	 * @return a fresh {@link AuthResponse}
	 */
	public static <T> AuthResponse<T> of(final AuthResponseCode code, final String message, final T data) {
		return new AuthResponse<T>(code, message, data);
	}

	/**
	 * Build a response from a textual integer code and a message. The status
	 * defaults to {@link AuthConstants#RT_SUCCESS}.
	 *
	 * @param <T>     the data type
	 * @param code    the integer code, as a string
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse}
	 * @throws NumberFormatException if {@code code} cannot be parsed as an
	 *                               integer
	 */
	public static <T> AuthResponse<T> of(final String code, final String message) {
		return new AuthResponse<T>(Integer.parseInt(code), message);
	}

	/**
	 * Build a response from an integer code and a message. The status
	 * defaults to {@link AuthConstants#RT_SUCCESS}.
	 *
	 * @param <T>     the data type
	 * @param code    the integer code
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse}
	 */
	public static <T> AuthResponse<T> of(final int code, final String message) {
		return new AuthResponse<T>(code, message);
	}

	/**
	 * Build a response from a textual integer code, a status and a message.
	 *
	 * @param <T>     the data type
	 * @param code    the integer code, as a string
	 * @param status  the coarse-grained status string
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse}
	 * @throws NumberFormatException if {@code code} cannot be parsed as an
	 *                               integer
	 */
	public static <T> AuthResponse<T> of(final String code, final String status, final String message) {
		return of(Integer.parseInt(code), status, message, null);
	}

	/**
	 * Build a response from an integer code, a status and a message.
	 *
	 * @param <T>     the data type
	 * @param code    the integer code
	 * @param status  the coarse-grained status string
	 * @param message the human-readable message
	 * @return a fresh {@link AuthResponse}
	 */
	public static <T> AuthResponse<T> of(final int code, final String status, final String message) {
		return of(code, status, message, null);
	}

	/**
	 * Build a fully customisable response from raw values.
	 *
	 * @param <T>     the data type
	 * @param code    the integer code
	 * @param status  the coarse-grained status string
	 * @param message the human-readable message
	 * @param data    the data payload to attach
	 * @return a fresh {@link AuthResponse}
	 */
	public static <T> AuthResponse<T> of(final int code, final String status, final String message, final T data) {
		return new AuthResponse<T>(code, status, message, data);
	}

	/**
	 * @return the integer code carried by this response
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the coarse-grained status string carried by this response
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the human-readable message carried by this response
	 */
	public String getmessage() {
		return message;
	}

	/**
	 * @return the data payload carried by this response (may be {@code null})
	 */
	public T getData() {
		return data;
	}

	/**
	 * Serialise the response into a {@link Map} with the keys {@code code},
	 * {@code status}, {@code message} and {@code data}.
	 *
	 * @return a mutable map representation of this response
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("code", code);
		rtMap.put("status", status);
		rtMap.put("message", message);
		rtMap.put("data", data);
		return rtMap;
	}

}