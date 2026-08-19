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

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration properties that control the HTTP Public Key Pinning (HPKP)
 * response header written by the security filter.
 *
 * <p>Mirrors the equivalent Spring Security configuration and is typically
 * bound from the application configuration under the
 * {@code spring.security.headers.hpkp} prefix.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see HeaderHstsProperties
 * @see <a href="https://tools.ietf.org/html/rfc7469">RFC 7469</a>
 */
@Getter
@Setter
@ToString
public class HeaderHpkpProperties {

	/**
	 * When {@code true}, the filter writes the {@code Public-Key-Pins} header.
	 * Defaults to {@code false}.
	 */
	private boolean enabled = false;

	/**
	 * If {@code true}, the pinning policy applies to this pinned host as well
	 * as any subdomains of the host's domain name. The default is
	 * {@code false}.
	 *
	 * <p>See <a href="https://tools.ietf.org/html/rfc7469#section-2.1.3">RFC 7469
	 * Section 2.1.3</a> for additional details.</p>
	 */
	private boolean includeSubDomains;

	/**
	 * The maximum amount of time (in seconds) to regard the host as pinned.
	 * Sets the value (in seconds) for the {@code max-age} directive of the
	 * {@code Public-Key-Pins} header. The default is 60 days.
	 *
	 * <p>See <a href="https://tools.ietf.org/html/rfc7469#section-2.1.2">RFC 7469
	 * Section 2.1.2</a> for additional details.</p>
	 */
	private long maxAgeInSeconds;

	/**
	 * If {@code true}, the browser should not terminate the connection with
	 * the server. The default is {@code true}.
	 *
	 * <p>See <a href="https://tools.ietf.org/html/rfc7469#section-2.1">RFC 7469
	 * Section 2.1</a> for additional details.</p>
	 */
	private boolean reportOnly = true;

	/**
	 * The URI where the browser should send the pin-validation report.
	 *
	 * <p>See <a href="https://tools.ietf.org/html/rfc7469#section-2.1.4">RFC 7469
	 * Section 2.1.4</a> for additional details.</p>
	 */
	private String reportUri;

	/**
	 * A list of base64-encoded SPKI fingerprints to add to the {@code pin-}
	 * directive of the {@code Public-Key-Pins} header.
	 *
	 * <p>See <a href="https://tools.ietf.org/html/rfc7469#section-2.1.1">RFC 7469
	 * Section 2.1.1</a> for additional details.</p>
	 */
	private String[] sha256Pins = new String[0];

	/**
	 * Map of base64-encoded SPKI fingerprints to cryptographic hash algorithm
	 * pairs, used to set the value of the {@code pin-} directive of the
	 * {@code Public-Key-Pins} header.
	 *
	 * <p>See <a href="https://tools.ietf.org/html/rfc7469#section-2.1.1">RFC 7469
	 * Section 2.1.1</a> for additional details.</p>
	 */
	private Map<String, String> pins = new HashMap<String, String>();

}