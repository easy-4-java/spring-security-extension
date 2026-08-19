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
package org.springframework.security.extension.authentication;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * SPI used by the easy-4-java security stack to track the number of failed
 * authentication attempts per request.
 *
 * <p>Implementations are free to back the counter with any store that can be
 * keyed off a request attribute (typically the session id or a username).
 * The default request attribute name is {@link #DEFAULT_RETRY_TIMES_KEY_PARAM_NAME}.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 */
public interface AuthenticatingFailureCounter {

	/** Default request attribute name that carries the failure counter value. */
	public static final String DEFAULT_RETRY_TIMES_KEY_PARAM_NAME = "failureRetries";

	/**
	 * Return the current failure count for the given key attribute.
	 *
	 * @param request                 the servlet request that triggered the lookup
	 * @param response                the servlet response associated with the request
	 * @param retryTimesKeyAttribute  the attribute name used to key the counter
	 *                                (e.g. username or session id)
	 * @return the current failure count
	 */
	int get(ServletRequest request, ServletResponse response, String retryTimesKeyAttribute);

	/**
	 * Increment the failure count for the given key attribute by one.
	 *
	 * @param request                 the servlet request that triggered the
	 *                                increment
	 * @param response                the servlet response associated with the
	 *                                request
	 * @param retryTimesKeyAttribute  the attribute name used to key the counter
	 */
	void increment(ServletRequest request, ServletResponse response, String retryTimesKeyAttribute);

}