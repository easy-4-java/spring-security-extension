/*
 * Copyright (c) 2018-present, easy-4-java (https://github.com/easy-4-java).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.extension.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request body model consumed by the REST-mode login endpoint.
 *
 * <p>The model binds the three JSON properties {@code username},
 * {@code password} and {@code captcha} via the Jackson {@link JsonCreator}
 * annotation so that the endpoint can be called from a JSON client without
 * any additional deserialisation configuration.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 */
public class PostLoginRequest {

	/** The account used for authentication (required). */
    private String username;
    /** The password used for authentication (required). */
    private String password;
    /** The captcha presented by the user, if required (optional). */
    private String captcha;

	/**
	 * Create a new login request body.
	 *
	 * @param username the username presented by the client
	 * @param password the password presented by the client
	 * @param captcha  the captcha presented by the client (may be {@code null})
	 */
    @JsonCreator
    public PostLoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("captcha") String captcha) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
    }

	/**
	 * @return the username presented by the client
	 */
    public String getUsername() {
        return username;
    }

	/**
	 * @return the password presented by the client
	 */
    public String getPassword() {
        return password;
    }

	/**
	 * @return the captcha presented by the client (may be {@code null})
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha the captcha presented by the client (may be {@code null})
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * @param username the username presented by the client
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password presented by the client
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}