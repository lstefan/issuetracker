package com.endava.issuetracker.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "ISSUE")
public class Issue extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 6411858499754836497L;

	@Column(name = "TITLE", length = 100)
	private String title;

	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	@Enumerated(EnumType.STRING)
	private State state;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "PROJECT_ID", foreignKey = @ForeignKey(name = "FK_ISSUE_PROJECT_ID"))
	private Project project;

	@Column(name = "SEVERITY")
	@Enumerated(EnumType.STRING)
	private Severity severity;

	@Column(name = "PRIORITY")
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Column(name = "RESOLUTION")
	@Enumerated(EnumType.STRING)
	private Resolution resolution;

	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(name = "CREATED")
	private Timestamp created;

	@Column(name = "MODIFIED")
	private Timestamp modified;

	@ManyToOne
	@JoinColumn(name = "OPEN_VERSION_ID", foreignKey = @ForeignKey(name = "FK_ISSUE_OPEN_VERSION_ID"))
	private Version openOnVersion;

	@ManyToOne
	@JoinColumn(name = "FIX_VERSION_ID", foreignKey = @ForeignKey(name = "FK_ISSUE_FIXED_VERSION_ID"))
	private Version fixedOnVersion;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY", foreignKey = @ForeignKey(name = "FK_ISSUE_CREATED_BY_ID"))
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "MODIFIED_BY", foreignKey = @ForeignKey(name = "FK_ISSUE_MODIFIED_BY_ID"))
	private User modifiedBy;

	@ManyToOne
	@JoinColumn(name = "ASSIGNED", foreignKey = @ForeignKey(name = "FK_ISSUE_ASSIGNED_ID"))
	private User assigned;

	@OneToMany
	private List<IssueHistory> issueHistory;

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Version getOpenOnVersion() {
		return openOnVersion;
	}

	public void setOpenOnVersion(Version openOnVersion) {
		this.openOnVersion = openOnVersion;
	}

	public Version getFixedOnVersion() {
		return fixedOnVersion;
	}

	public void setFixedOnVersion(Version fixedOnVersion) {
		this.fixedOnVersion = fixedOnVersion;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public User getAssigned() {
		return assigned;
	}

	public void setAssigned(User assigned) {
		this.assigned = assigned;
	}

	public List<IssueHistory> getIssueHistory() {
		return issueHistory;
	}

	public void setIssueHistory(List<IssueHistory> issueHistory) {
		this.issueHistory = issueHistory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

}
