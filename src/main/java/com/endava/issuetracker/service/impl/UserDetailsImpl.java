package com.endava.issuetracker.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.endava.issuetracker.domain.Role;
import com.endava.issuetracker.domain.RoleEnum;
import com.endava.issuetracker.service.util.SocialMediaProvider;

/**
 * 
 * @author Livia Stefan
 *
 */
public class UserDetailsImpl extends SocialUser {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String firstName;

	private String lastName;

	private RoleEnum role;

	private SocialMediaProvider socialSignInProvider;

	public UserDetailsImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public UserDetailsImpl(String username, String password, Set<GrantedAuthority> authotorities) {
		super(username, password, authotorities);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public SocialMediaProvider getSocialSignInProvider() {
		return socialSignInProvider;
	}

	public void setSocialSignInProvider(SocialMediaProvider socialSignInProvider) {
		this.socialSignInProvider = socialSignInProvider;
	}

	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder {

		private Long id;

		private String username;

		private String firstName;

		private String lastName;

		private String password;

		private RoleEnum role;

		private SocialMediaProvider socialSignInProvider;

		private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder password(String password) {
			if (password == null) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

		public Builder role(RoleEnum roleEnum) {
			this.role = roleEnum;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleEnum.toString());
			this.authorities.add(authority);

			return this;
		}

		public Builder socialSignInProvider(SocialMediaProvider socialSignInProvider) {
			this.socialSignInProvider = socialSignInProvider;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public UserDetailsImpl build() {
			UserDetailsImpl user = new UserDetailsImpl(username, password, authorities);
			
			user.id = id;
			user.firstName = firstName;
			user.lastName = lastName;
			user.role = role;
			user.socialSignInProvider = socialSignInProvider;
			
			return user;
		}
	}
}
