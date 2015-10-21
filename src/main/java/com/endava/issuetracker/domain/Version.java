package com.endava.issuetracker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "VERSION")
public class Version extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -5781305450696572308L;

	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name = "PROJECT_ID", foreignKey = @ForeignKey(name = "FK_VERSION_PROJECT_ID"))
	private Project project;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}	

}
