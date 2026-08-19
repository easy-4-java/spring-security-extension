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
package org.springframework.security.extension.authentication.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Strategy abstraction for verifying and storing captcha values across HTTP
 * requests.
 *
 * <p>Implementations are typically backed by a server-side store (such as
 * the HTTP session or a Redis cache) so that the captcha issued during the
 * previous response can be matched against the value posted by the client.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see NullCaptchaResolver
 */
public interface CaptchaResolver {

	/**
	 * Validate the captcha supplied with the given request.
	 *
	 * @param request the servlet request
	 * @param capText the captcha text supplied by the client
	 * @return {@code true} if the captcha is valid
	 */
	boolean validCaptcha(HttpServletRequest request, String capText);

	/**
	 * Store the supplied captcha so that it can be matched on a subsequent
	 * request.
	 *
	 * @param request  the servlet request
	 * @param response the servlet response
	 * @param capText  the new captcha text
	 * @param capDate  the timestamp at which the captcha was issued
	 */
	void setCaptcha(HttpServletRequest request, HttpServletResponse response, String capText, Date capDate);

}