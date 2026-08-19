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
package org.springframework.security.extension.property.header;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Configuration properties that control the {@code X-Frame-Options} response
 * header written by the security filter.
 *
 * <p>Mirrors the equivalent Spring Security configuration and is typically
 * bound from the application configuration under the
 * {@code spring.security.headers.frame-options} prefix.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see HeaderContentTypeOptionsProperties
 * @see HeaderXssProtectionProperties
 */
@Getter
@Setter
@ToString
public class HeaderFrameOptionsProperties {

	/**
	 * When {@code true}, the filter writes the {@code X-Frame-Options} header.
	 * Defaults to {@code false}.
	 */
	private boolean enabled = false;
	/**
	 * When {@code true}, framing any content from this application is denied.
	 * Defaults to {@code false}.
	 */
	private boolean deny = false;
	/**
	 * When {@code true}, only requests from the same origin are allowed to
	 * frame this application. For example, if the application is hosted on
	 * {@code example.com}, then {@code example.com} may frame it, but
	 * {@code evil.com} may not. Defaults to {@code false}.
	 */
	private boolean sameOrigin = false;

}