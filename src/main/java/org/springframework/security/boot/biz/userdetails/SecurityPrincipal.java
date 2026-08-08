package org.springframework.security.boot.biz.userdetails;

import io.github.easy4j.jwt.JwtPayload;
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
 * @author [@Loong Wan](https://github.com/loong10k)
 */
@SuppressWarnings("serial")
/**
 * Extended principal object that carries additional security metadata for authenticated users.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 */
public class SecurityPrincipal extends User implements Cloneable {

	protected static final String ADMIN_STRING = "admin";

		private String uid;
		private String uuid;
		private String ukey;
		private String ucode;
		private String rid;
		private String rkey;
		private String rcode;
	    private boolean bound = Boolean.FALSE;
        private boolean initial = Boolean.FALSE;
		private boolean verify = Boolean.FALSE;
    	private String sign;
		private String authType;
		private double longitude;
		private double latitude;
		private List<Object> roles;
		private Set<String> perms = new HashSet<>();
		private Map<String, Object> profile = new HashMap<String, Object>();

	public SecurityPrincipal(String username, String password, String... roles) {
		super(username, password, roleAuthorities(Arrays.asList(roles)));
	}

	public static Collection<? extends GrantedAuthority> roleAuthorities(List<String> roles) {
		if (roles == null) {
			throw new InsufficientAuthenticationException("User has no roles assigned");
		}
		List<GrantedAuthority> authorities = roles.stream().map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());

		return authorities;
	}

	public SecurityPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public SecurityPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUkey() {
		return ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRkey() {
		return rkey;
	}

	public void setRkey(String rkey) {
		this.rkey = rkey;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public boolean isBound() {
		return bound;
	}

	public void setBound(boolean bound) {
		this.bound = bound;
	}

	public boolean isInitial() {
		return initial;
	}

	public void setInitial(boolean initial) {
		this.initial = initial;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public boolean isVerify() {
		return verify;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public List<Object> getRoles() {
		return roles;
	}

	public void setRoles(List<Object> roles) {
		this.roles = roles;
	}

	public Set<String> getPerms() {
		return perms;
	}

	public void setPerms(Set<String> perms) {
		this.perms = perms;
	}

	public Map<String, Object> getProfile() {
		return profile;
	}

	public void setProfile(Map<String, Object> profile) {
		this.profile = profile;
	}

	public boolean isAdmin() {
		if(CollectionUtils.isEmpty(roles)) {
			return false;
		}
		return CollectionUtils.contains(getRoles().iterator(), ADMIN_STRING) || StringUtils.equalsIgnoreCase(ADMIN_STRING, this.getRkey()) || StringUtils.equalsIgnoreCase(ADMIN_STRING, this.getRid());
	}

	public boolean hasRole(String role) {
		if(!StringUtils.isNoneBlank(role)) {
			return false;
		}
		if(CollectionUtils.isEmpty(roles)) {
			return false;
		}
		return false;
	}

	public boolean hasAnyRole(String... roles) {
		if(!StringUtils.isNoneBlank(roles)) {
			return false;
		}
		if(CollectionUtils.isEmpty(getRoles())) {
			return false;
		}
		return false;
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
