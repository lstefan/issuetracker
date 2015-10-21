package com.endava.issuetracker.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.endava.issuetracker.service.util.SocialMediaProvider;

@Entity
@Table(name = "USER")
public class User extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -5430119865810405526L;

	@Column(name = "FIRSTNAME", length = 45)
	private String firstname;

	@Column(name = "LASTNAME", length = 45)
	private String lastname;

	@Column(name = "EMAIL", length = 45, unique=true)
	private String email;

/*	@Column(name = "USERNAME", unique = true, length = 45)
	private String username;*/

	@Column(name = "PASSWORD", unique = true, length = 255)
	private String password;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaProvider signInProvider;	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
	private List<Project> ownedProjects;
	
/*	@ManyToMany()
	@JoinTable(name = "USER_ROLE",  joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	private List<Role> roles;
*/
 
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "assignedUsers")
    /*private List<Project> assignedProjects;*/
    private Set<Project> assignedProjects;
    
	public Set<Project> getAssignedProjects() {
		return assignedProjects;
	}

	public void setAssignedProjects(Set<Project> assignedProjects) {
		this.assignedProjects = assignedProjects;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Project> getOwnedProjects() {
		return ownedProjects;
	}

	public void setOwnedProjects(List<Project> projects) {
		this.ownedProjects = projects;
	}

//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}
	
	
	
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("signInProvider", this.getSignInProvider())
                .toString();
    }


	
	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public SocialMediaProvider getSignInProvider() {
		return signInProvider;
	}

	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}


	public void setSignInProvider(SocialMediaProvider signInProvider) {
		this.signInProvider = signInProvider;
	}


		public static class Builder {

	        private User user;

	        public Builder() {
	            user = new User();
	            //user.role = RoleEnum.USER;
	        }

	        public Builder email(String email) {
	            user.email = email;
	            return this;
	        }

	        public Builder firstName(String firstName) {
	            user.firstname = firstName;
	            return this;
	        }

	        public Builder lastName(String lastName) {
	            user.lastname = lastName;
	            return this;
	        }

	        public Builder password(String password) {
	            user.password = password;
	            return this;
	        }

	        public Builder signInProvider(SocialMediaProvider signInProvider) {
	            user.signInProvider = signInProvider;
	            return this;
	        }
	        
	        public Builder role(RoleEnum role) {
	        	user.role = RoleEnum.USER;
	        	return this;
	        }

	        public User build() {
	            return user;
	        }
	    }	
		
	    public static Builder getBuilder() {
	        return new Builder();
	    }
	    
	    public RoleEnum getRole() {
	        return role;
	    }

}
