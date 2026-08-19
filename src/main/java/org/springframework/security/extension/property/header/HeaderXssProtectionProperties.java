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
 * Configuration properties that control the browser XSS protection header
 * written by the security filter.
 *
 * <p>Mirrors the equivalent Spring Security configuration and is typically
 * bound from the application configuration under the
 * {@code spring.security.headers.xss-protection} prefix.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see HeaderContentTypeOptionsProperties
 * @see HeaderFrameOptionsProperties
 */
@Getter
@Setter
@ToString
public class HeaderXssProtectionProperties {

	/**
	 * If {@code true}, the {@code X-XSS-Protection} header is written.
	 */
	private boolean enabled;

	/**
	 * If {@code false}, the mode is not specified as {@code blocked}. In that
	 * case, any malicious content is attempted to be sanitised. If
	 * {@code true}, the content is replaced with the literal string
	 * {@code "#"}.
	 */
	private boolean block;

}