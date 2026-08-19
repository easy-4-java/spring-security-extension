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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Configuration properties that control the behaviour of the logout filter.
 *
 * <p>These properties mirror a subset of Spring Security's logout filter
 * configuration and are typically bound from the application configuration
 * under the {@code spring.security.logout} prefix.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see SecurityRedirectProperties
 * @see SecurityEntryPointProperties
 */
@Getter
@Setter
@ToString
public class SecurityLogoutProperties {

	/** The endpoint that triggers session termination. */
	private String logoutUrl;
	/** URL patterns that the logout filter will respond to. Defaults to {@code /logout}. */
	private String pathPatterns = "/logout";
	/** The redirect target served after a successful session termination. */
	private String logoutSuccessUrl;

	/** Whether the underlying HTTP session should be invalidated on logout. Defaults to {@code true}. */
	private boolean invalidateHttpSession = true;
	/** Whether the {@code SecurityContext} should be cleared on logout. Defaults to {@code true}. */
	private boolean clearAuthentication = true;

}