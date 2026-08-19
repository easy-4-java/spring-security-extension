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
 * Collection of string constants used throughout the authentication and
 * authorisation layers of the easy-4-java security stack.
 *
 * <p>The constants fall into two groups:</p>
 * <ul>
 *     <li>principal attribute names ({@link #UID}, {@link #UKEY},
 *         {@link #UCODE}, {@link #RID}, {@link #RKEY});</li>
 *     <li>response status markers ({@link #RT_SUCCESS}, {@link #RT_FAIL},
 *         {@link #RT_ERROR}).</li>
 * </ul>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see AuthResponse
 * @see AuthResponseCode
 */
public class AuthConstants {

	/** Principal attribute name for the upstream numeric user id. */
	public static final String UID = "uid";
	/** Principal attribute name for the business-level unique user key. */
	public static final String UKEY = "ukey";
	/** Principal attribute name for the business-level unique user code. */
	public static final String UCODE = "ucode";
	/** Principal attribute name for the upstream numeric role id. */
	public static final String RID = "rid";
	/** Principal attribute name for the business-level unique role key. */
	public static final String RKEY = "rkey";

	/** Status string indicating a successful response. */
	public static final String RT_SUCCESS = "success";
	/** Status string indicating a failed response (recoverable client error). */
	public static final String RT_FAIL = "fail";
	/** Status string indicating an erroneous response (server-side error). */
	public static final String RT_ERROR = "error";

}