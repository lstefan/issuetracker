package com.endava.issuetracker.domain;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "PROJECT")
public class Project extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -7269934211110078857L;

	@Column(name = "PROJECT_NAME", length = 45)
	private String projectName;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	private Set<Version> versions;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_PROJECT_USER_ID"))
	private User owner;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "RELEASE_NOTES")
	private String releaseNotes;
	
	@Column(name = "CREATED")
	private Timestamp created;
		
	@Column(name = "MODIFIED")
	private Timestamp modified;		
	
	@OneToMany(mappedBy = "project")
	private Set<Issue> issues;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "PROJECT_USERS",  joinColumns = {@JoinColumn(name = "PROJECT_ID")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
	private Set<User> assignedUsers;
	
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Version> getVersions() {
		return versions;
	}

	public void setVersions(Set<Version> versions) {
		this.versions = versions;
		
	}

	public Version getLastVersion() {
		//return versions.get(versions.size() - 1);
		   final Iterator<Version> itr = versions.iterator();
		    Version lastElement =  itr.next();
		    while(itr.hasNext()) {
		        lastElement=itr.next();
		    }
		    return lastElement;
	}
	
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	public Set<User> getAssignedUsers() {
		return assignedUsers;
	}

	public void setAssignedUsers(Set<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public String getReleaseNotes() {
		return releaseNotes;
	}

	public void setReleaseNotes(String releaseNotes) {
		this.releaseNotes = releaseNotes;
	}
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	
}
