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
 * Configuration properties that control the HTTP Strict Transport Security
 * (HSTS) response header written by the security filter.
 *
 * <p>Mirrors the equivalent Spring Security configuration and is typically
 * bound from the application configuration under the
 * {@code spring.security.headers.hsts} prefix.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see HeaderHpkpProperties
 * @see <a href="https://tools.ietf.org/html/rfc6797">RFC 6797</a>
 */
@Getter
@Setter
@ToString
public class HeaderHstsProperties {

	/**
	 * When {@code true}, the filter writes the HSTS header. Defaults to
	 * {@code false}.
	 */
	private boolean enabled = false;

	/**
	 * If {@code true}, subdomains should be considered HSTS hosts too.
	 * The default is {@code true}.
	 *
	 * <p>See <a href="https://tools.ietf.org/html/rfc6797#section-6.1.2">RFC 6797
	 * Section 6.1.2</a> for additional details.</p>
	 */
	private boolean includeSubDomains;

	/**
	 * The maximum amount of time (in seconds) to consider this domain as a
	 * known HSTS host. Sets the value (in seconds) for the {@code max-age}
	 * directive of the {@code Strict-Transport-Security} header.
	 *
	 * <p>The default is one year. See <a
	 * href="https://tools.ietf.org/html/rfc6797#section-6.1.1">RFC 6797 Section
	 * 6.1.1</a> for additional details.</p>
	 */
	private long maxAgeInSeconds;

}