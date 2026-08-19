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
package org.springframework.security.extension.userdetails;

import io.github.easy4j.jwt.JwtPayload.RolePair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Extended Spring Security {@link User} that carries the domain-specific
 * identity (uid, uuid, ukey, ucode) and role/permission information required
 * by the easy-4-java security stack.
 *
 * <p>Beyond the standard Spring Security username/password/authority trio,
 * this principal exposes:</p>
 * <ul>
 *     <li>multiple stable user identifiers ({@code uid}, {@code uuid},
 *         {@code ukey}, {@code ucode});</li>
 *     <li>role identifier triple ({@code rid}, {@code rkey}, {@code rcode});</li>
 *     <li>lifecycle flags such as {@code bound}, {@code initial} and
 *         {@code verify};</li>
 *     <li>a profile map, a permission set, an optional request sign and the
 *         latest geographic coordinates captured from the client.</li>
 * </ul>
 *
 * <p>The class is cloneable and is intended to be stored as the authentication
 * principal by Spring Security so that downstream code can rely on a richer
 * type than the default {@link User}.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see User
 * @see UserProfilePayload
 * @see JwtPayloadRepository
 */
@SuppressWarnings("serial")
public class SecurityPrincipal extends User implements Cloneable {

	/** Constant that identifies the built-in administrator role. */
	protected static final String ADMIN_STRING = "admin";

	/** Internal numeric identifier of the user in the upstream table. */
	private String uid;
	/** Universally unique identifier (external stable id). */
	private String uuid;
	/** Business-level unique key of the user (e.g. employee number). */
	private String ukey;
	/** Business-level unique code of the user (e.g. internal job number). */
	private String ucode;
	/** Internal numeric identifier of the primary role in the role table. */
	private String rid;
	/** Business-level unique key of the primary role. */
	private String rkey;
	/** Business-level unique code of the primary role. */
	private String rcode;
	/** Whether the user has completed the binding step (e.g. email or phone binding). */
	private boolean bound = Boolean.FALSE;
	/** Whether the user has completed the profile initialisation step. */
	private boolean initial = Boolean.FALSE;
	/** Whether the user must perform multi-factor authentication. */
	private boolean verify = Boolean.FALSE;
	/** Optional request signature that can be validated by the server. */
	private String sign;
	/** Latest longitude reported by the client (optional). */
	private double longitude;
	/** Latest latitude reported by the client (optional). */
	private double latitude;
	/** Roles assigned to the user (as JWT role pairs). */
	private List<RolePair> roles;
	/** Fine-grained permission markers granted to the user. */
	private Set<String> perms = new HashSet<>();
	/** Free-form profile data, keyed by attribute name. */
	private Map<String, Object> profile = new HashMap<String, Object>();

	/**
	 * Create a principal using a username, password and a variable-arity list
	 * of role names. Each role name is converted to a Spring Security
	 * {@link SimpleGrantedAuthority}.
	 *
	 * @param username the username presented to Spring Security
	 * @param password the (already-encoded) password
	 * @param roles    zero or more role names; null triggers an
	 *                 {@link InsufficientAuthenticationException}
	 * @see #roleAuthorities(List)
	 */
	public SecurityPrincipal(String username, String password, String... roles) {
		super(username, password, roleAuthorities(Arrays.asList(roles)));
	}

	/**
	 * Convert a list of role names into Spring Security authorities.
	 *
	 * @param roles role names; must not be {@code null}
	 * @return a non-null collection of {@link SimpleGrantedAuthority}
	 *         instances, one per role name
	 * @throws InsufficientAuthenticationException if {@code roles} is {@code null}
	 */
	public static Collection<? extends GrantedAuthority> roleAuthorities(List<String> roles) {
		if (roles == null) {
			throw new InsufficientAuthenticationException("User has no roles assigned");
		}
		List<GrantedAuthority> authorities = roles.stream().map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());

		return authorities;
	}

	/**
	 * Create a principal using a pre-built collection of authorities.
	 *
	 * @param username    the username presented to Spring Security
	 * @param password    the (already-encoded) password
	 * @param authorities the authorities granted to the user
	 */
	public SecurityPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	/**
	 * Create a principal with full control over the account-status flags.
	 *
	 * @param username               the username presented to Spring Security
	 * @param password               the (already-encoded) password
	 * @param enabled                whether the account is enabled
	 * @param accountNonExpired      whether the account is non-expired
	 * @param credentialsNonExpired  whether the credentials are non-expired
	 * @param accountNonLocked       whether the account is non-locked
	 * @param authorities            the authorities granted to the user
	 */
	public SecurityPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	/**
	 * @return the upstream numeric user id
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the upstream numeric user id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the external unique user id
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the external unique user id
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the business-level unique key of the user
	 */
	public String getUkey() {
		return ukey;
	}

	/**
	 * @param ukey the business-level unique key of the user
	 */
	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	/**
	 * @return the business-level unique code of the user
	 */
	public String getUcode() {
		return ucode;
	}

	/**
	 * @param ucode the business-level unique code of the user
	 */
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	/**
	 * @return the internal id of the primary role
	 */
	public String getRid() {
		return rid;
	}

	/**
	 * @param rid the internal id of the primary role
	 */
	public void setRid(String rid) {
		this.rid = rid;
	}

	/**
	 * @return the business-level unique key of the primary role
	 */
	public String getRkey() {
		return rkey;
	}

	/**
	 * @param rkey the business-level unique key of the primary role
	 */
	public void setRkey(String rkey) {
		this.rkey = rkey;
	}

	/**
	 * @return the business-level unique code of the primary role
	 */
	public String getRcode() {
		return rcode;
	}

	/**
	 * @param rcode the business-level unique code of the primary role
	 */
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	/**
	 * @return whether the binding step has been completed
	 */
	public boolean isBound() {
		return bound;
	}

	/**
	 * @param bound whether the binding step has been completed
	 */
	public void setBound(boolean bound) {
		this.bound = bound;
	}

	/**
	 * @return whether the profile initialisation step has been completed
	 */
	public boolean isInitial() {
		return initial;
	}

	/**
	 * @param initial whether the profile initialisation step has been completed
	 */
	public void setInitial(boolean initial) {
		this.initial = initial;
	}

	/**
	 * @param verify whether multi-factor authentication is required
	 */
	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	/**
	 * @return whether multi-factor authentication is required
	 */
	public boolean isVerify() {
		return verify;
	}

	/**
	 * @return the optional request signature
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the optional request signature
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the latest reported longitude, or {@code 0.0} if none
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the latest reported longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latest reported latitude, or {@code 0.0} if none
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latest reported latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the assigned roles as JWT role pairs (may be {@code null})
	 */
	public List<RolePair> getRoles() {
		return roles;
	}

	/**
	 * @param roles the assigned roles as JWT role pairs
	 */
	public void setRoles(List<RolePair> roles) {
		this.roles = roles;
	}

	/**
	 * @return the set of permission markers granted to the user
	 */
	public Set<String> getPerms() {
		return perms;
	}

	/**
	 * @param perms the set of permission markers granted to the user
	 */
	public void setPerms(Set<String> perms) {
		this.perms = perms;
	}

	/**
	 * @return the free-form profile data map
	 */
	public Map<String, Object> getProfile() {
		return profile;
	}

	/**
	 * @param profile the free-form profile data map
	 */
	public void setProfile(Map<String, Object> profile) {
		this.profile = profile;
	}

	/**
	 * Determine whether the user holds the built-in administrator role.
	 *
	 * <p>The check is performed in three places:</p>
	 * <ol>
	 *     <li>the {@code key} of any role in {@link #getRoles()};</li>
	 *     <li>the {@link #getRkey() primary role key};</li>
	 *     <li>the {@link #getRid() primary role id}.</li>
	 * </ol>
	 *
	 * @return {@code true} if the user is an administrator
	 */
	public boolean isAdmin() {
		if(CollectionUtils.isEmpty(roles)) {
			return false;
		}
		return CollectionUtils.contains(getRoles().iterator(), ADMIN_STRING) || StringUtils.equalsIgnoreCase(ADMIN_STRING, this.getRkey()) || StringUtils.equalsIgnoreCase(ADMIN_STRING, this.getRid());
	}

	/**
	 * Determine whether the user holds a specific role, by comparing the
	 * {@code key} field of each assigned {@link RolePair} case-insensitively.
	 *
	 * @param role the role key to look up
	 * @return {@code true} if the role is assigned, {@code false} otherwise
	 */
	public boolean hasRole(String role) {
		if(!StringUtils.isNoneBlank(role)) {
			return false;
		}
		if(CollectionUtils.isEmpty(roles)) {
			return false;
		}
		return roles.stream().anyMatch(entry -> StringUtils.equalsIgnoreCase(entry.getKey(), role));
	}

	/**
	 * Determine whether the user holds at least one of the supplied role keys.
	 *
	 * @param roles the role keys to look up
	 * @return {@code true} if at least one of the supplied roles is assigned
	 */
	public boolean hasAnyRole(String... roles) {
		if(!StringUtils.isNoneBlank(roles)) {
			return false;
		}
		if(CollectionUtils.isEmpty(getRoles())) {
			return false;
		}
		return CollectionUtils.containsAny(getRoles(), Arrays.asList(roles));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SecurityPrincipal user = (SecurityPrincipal) o;
		if (uid != null ? !uid.equals(user.getUid()) : user.getUid() != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return uid != null ? uid.hashCode() : 0;
	}

	@Override
	public String toString() {
		return " User {" + "userid=" + uid + ", username='" + getUsername() + '\'' + ", password='" + getPassword()
				+ '\'' + ", enabled='" + isEnabled() + '\'' + ", accountNonExpired="
				+ isAccountNonExpired() + ", credentialsNonExpired=" + isCredentialsNonExpired() + ", accountNonLocked="
				+ isAccountNonLocked() + '}';
	}


	/**
	 * Convert this principal into a serialisable {@link UserProfilePayload}.
	 *
	 * <p>The {@code perms} collection is defensively copied into a new
	 * {@link HashSet}; when the {@code profile} map is empty a fresh empty
	 * map is exposed on the payload, otherwise the same reference is shared.</p>
	 *
	 * @return a fresh {@link UserProfilePayload} that mirrors this principal's
	 *         user/role/permission state
	 */
	public UserProfilePayload toPayload(){

		UserProfilePayload payload = new UserProfilePayload();

		payload.setUid(this.getUid());
		payload.setUuid(this.getUuid());
		payload.setUkey(this.getUkey());
		payload.setUcode(this.getUcode());
		payload.setPerms(new HashSet<String>(perms));
		payload.setRid(this.getRid());
		payload.setRkey(this.getRkey());
		payload.setRcode(this.getRcode());
		payload.setRoles(this.getRoles());
		payload.setBound(this.isBound());
		payload.setInitial(this.isInitial());
		payload.setVerify(this.isVerify());

		if (CollectionUtils.isEmpty(this.getProfile())) {
			payload.setProfile(new HashMap<>(0));
		} else {
			payload.setProfile(this.getProfile());
		}
		return payload;

	}

}