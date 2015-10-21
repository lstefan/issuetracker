package com.endava.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "ISSUE_HISTORY")
public class IssueHistory extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -9085903230584327243L;

	@ManyToOne
	@JoinColumn(name = "ISSUE_ID",foreignKey = @ForeignKey(name = "FK_ISSUE_ISSUE_ID"))
	private Issue issue;
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
}
