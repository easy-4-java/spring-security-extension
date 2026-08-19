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
 * Configuration properties that control the {@code AuthenticationEntryPoint}
 * behaviour used by the easy-4-java security stack.
 *
 * <p>The properties typically bound from the application configuration under
 * the {@code spring.security.entry-point} prefix allow the entry point to
 * force HTTPS and to choose between a redirect-style and a forward-style
 * dispatch when the unauthenticated request must be answered.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see SecurityRedirectProperties
 * @see SecurityLogoutProperties
 */
@Getter
@Setter
@ToString
public class SecurityEntryPointProperties {

	/** When {@code true}, the entry point forces an HTTPS redirect. Defaults to {@code false}. */
	private boolean forceHttps = false;
	/** When {@code true}, the entry point uses a server-side forward instead of a redirect. Defaults to {@code false}. */
	private boolean useForward = false;

}