package com.endava.issuetracker.domain;

public enum Priority {
	LOW("low"),
	MEDIUM("medium"),
	HIGH("high");
	
	private String name;
	
	private Priority(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
