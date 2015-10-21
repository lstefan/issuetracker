package com.endava.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "ROLE")
public class Role extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 8223073138885472794L;

	@Enumerated
	private RoleEnum description;
	
	
	public RoleEnum getDescription() {
		return description;
	}

	public void setDescription(RoleEnum description) {
		this.description = description;
	}
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
}
