package org.springframework.security.boot.biz.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object for REST-mode login requests, carrying the username, password,
 * and optional captcha fields. Supports JSON deserialization via Jackson annotations.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 */
public class PostLoginRequest {
	
	    private String username;
        private String password;
        private String captcha;
	
    @JsonCreator
    public PostLoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("captcha") String captcha) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
