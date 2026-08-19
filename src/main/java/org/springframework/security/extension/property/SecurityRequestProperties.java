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

/**
 * Placeholder configuration properties for request-scoped security settings.
 *
 * <p>Currently this class is intentionally empty &mdash; it exists so that
 * downstream modules can extend it with request-related knobs (such as header
 * filters, request matchers, etc.) without breaking the configuration binding
 * prefix.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see SecurityRedirectProperties
 * @see SecurityLogoutProperties
 */
public class SecurityRequestProperties {


}