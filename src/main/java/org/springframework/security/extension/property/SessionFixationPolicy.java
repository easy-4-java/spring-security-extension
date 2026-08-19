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
package org.springframework.security.extension.property;

import javax.servlet.http.HttpSession;

/**
 * Strategy that describes how the security framework should mitigate
 * session-fixation attacks by changing the session identifier after
 * authentication.
 *
 * <p>The values mirror Spring Security's
 * {@code SessionFixationProtectionStrategy} options and are typically used by
 * the HTTP security configuration to determine which
 * {@code SessionAuthenticationStrategy} should be installed.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see javax.servlet.http.HttpSession
 */
public enum SessionFixationPolicy {

	/**
	 * Specifies that the Servlet container-provided session fixation protection
	 * should be used. When a session authenticates, the Servlet 3.1 method
	 * {@code HttpServletRequest#changeSessionId()} is called to change the
	 * session id and retain all session attributes.
	 *
	 * <p>Using this option in a Servlet 3.0 or older container results in an
	 * {@link IllegalStateException}.</p>
	 */
	CHANGE_SESSION_ID,
	/**
	 * Specifies that a new session should be created and the session
	 * attributes from the original {@link HttpSession} should be retained.
	 */
	MIGRATE_SESSION,
	/**
	 * Specifies that a new session should be created, but the session
	 * attributes from the original {@link HttpSession} should not be
	 * retained.
	 */
	NEW_SESSION,
	/**
	 * Specifies that no session fixation protection should be enabled. This
	 * may be useful when other mechanisms for protecting against session
	 * fixation are already in place (for example when the application
	 * container provides its own protection). Otherwise, this option is not
	 * recommended.
	 */
	NONE;

	/**
	 * Compare this policy with another, returning {@code true} when they
	 * represent the same constant.
	 *
	 * @param policy the policy to compare against
	 * @return {@code true} when the two policies share the same ordinal
	 */
	public boolean equals(SessionFixationPolicy policy) {
		return this.compareTo(policy) == 0;
	}

}