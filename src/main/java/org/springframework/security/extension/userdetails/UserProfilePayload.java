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
package org.springframework.security.extension.userdetails;

import io.github.easy4j.jwt.JwtPayload;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.*;

/**
 * Carrier object that exposes the user's profile information to API clients.
 *
 * <p>This POJO is used to serialise the {@link SecurityPrincipal} (and the
 * underlying JWT payload) into a transport-friendly representation that is
 * documented through Swagger annotations. It is intentionally mutable so that
 * it can be populated from various authentication back-ends while still
 * benefiting from Lombok-generated getters, setters, equals, hashCode and
 * toString via the {@link Data} annotation.</p>
 *
 * <p>Each field is mapped to a swagger property via {@link ApiModelProperty}
 * so the resulting JSON contract is self-describing.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see SecurityPrincipal#toPayload()
 * @see io.github.easy4j.jwt.JwtPayload
 */
@ApiModel(value = "UserProfilePayload", description = "User profile payload returned to API clients")
@Data
public class UserProfilePayload {

	/**
	 * The internal numeric identifier of the user in the upstream table.
	 */
	@ApiModelProperty(name = "uid", dataType = "String", value = "The internal numeric identifier of the user in the upstream table")
	private String uid;

	/**
	 * The universally unique identifier of the user (external stable id).
	 */
	@ApiModelProperty(name = "uuid", dataType = "String", value = "The universally unique identifier of the user")
	private String uuid;

	/**
	 * Business-level unique key of the user (e.g. employee number).
	 */
	@ApiModelProperty(name = "ukey", dataType = "String", value = "Business-level unique key of the user")
	private String ukey;

	/**
	 * Business-level unique code of the user (e.g. internal job number).
	 */
	@ApiModelProperty(name = "ucode", dataType = "String", value = "Business-level unique code of the user")
	private String ucode;

	/**
	 * Internal identifier of the primary role in the role table.
	 */
	@ApiModelProperty(name = "rid", dataType = "String", value = "Internal identifier of the primary role")
	private String rid;

	/**
	 * Business-level unique key of the primary role.
	 */
	@ApiModelProperty(name = "rkey", dataType = "String", value = "Business-level unique key of the primary role")
	private String rkey;

	/**
	 * Business-level unique code of the primary role.
	 */
	@ApiModelProperty(name = "rcode", dataType = "String", value = "Business-level unique code of the primary role")
	private String rcode;

	/**
	 * The signed JSON Web Token issued for the current session.
	 */
	@ApiModelProperty(name = "token", dataType = "String", value = "The signed JSON Web Token issued for the current session")
	private String token;

	/**
	 * Indicates whether the user has completed the binding step (e.g. email or phone binding).
	 */
	@ApiModelProperty(name = "bound", dataType = "Boolean", value = "Whether the user has completed the binding step")
	private boolean bound = Boolean.FALSE;

	/**
	 * Indicates whether the user has completed the profile initialisation step.
	 */
	@ApiModelProperty(name = "initial", dataType = "Boolean", value = "Whether the user has completed the profile initialisation step")
	private boolean initial = Boolean.FALSE;

	/**
	 * Indicates whether the user must perform multi-factor authentication.
	 */
	@ApiModelProperty(name = "verify", dataType = "Boolean", value = "Whether the user must perform multi-factor authentication")
	private boolean verify = Boolean.FALSE;

	/**
	 * Free-form profile information keyed by attribute name.
	 */
	@ApiModelProperty(name = "profile", dataType = "java.util.Map<String, Object>", value = "Free-form profile information keyed by attribute name")
	private Map<String, Object> profile = new HashMap<>();

	/**
	 * The set of roles assigned to the user, expressed as JWT role pairs.
	 */
	@ApiModelProperty(name = "roles", dataType = "java.util.Set<String>", value = "Roles assigned to the user, expressed as JWT role pairs")
	private List<JwtPayload.RolePair> roles = new ArrayList<>();

	/**
	 * Fine-grained permission markers granted to the user.
	 */
	@ApiModelProperty(name = "perms", dataType = "java.util.Set<String>", value = "Fine-grained permission markers granted to the user")
	private Set<String> perms = new HashSet<>();

}