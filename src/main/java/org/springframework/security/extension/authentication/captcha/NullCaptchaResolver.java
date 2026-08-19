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
 * No-op {@link CaptchaResolver} that always reports the captcha as valid.
 *
 * <p>It is intended to be wired in by default when no captcha mechanism is
 * configured. Both {@link #validCaptcha(HttpServletRequest, String)} and
 * {@link #setCaptcha(HttpServletRequest, HttpServletResponse, String, Date)}
 * are essentially pass-throughs.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see CaptchaResolver
 */
public class NullCaptchaResolver implements CaptchaResolver {

	/**
	 * Always returns {@code true}.
	 *
	 * @param request the servlet request
	 * @param capText the captcha text supplied by the client
	 * @return {@code true}
	 */
	@Override
	public boolean validCaptcha(HttpServletRequest request, String capText) {
		return true;
	}

	/**
	 * No-op implementation that discards the supplied captcha.
	 *
	 * @param request  the servlet request
	 * @param response the servlet response
	 * @param capText  the new captcha text
	 * @param capDate  the timestamp at which the captcha was issued
	 */
	@Override
	public void setCaptcha(HttpServletRequest request, HttpServletResponse response, String capText, Date capDate) {

	}

}