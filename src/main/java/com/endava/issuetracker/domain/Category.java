package com.endava.issuetracker.domain;

public enum Category {

	BUG("bug"),
	IMPROVEMENT("improvement");

	private String name;
	
	private Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
