package com.endava.issuetracker.domain;

public enum State {

	OPEN("open"),
	IN_PROGRESS("in progress"),
	RESOLVED("resolved"),
	CLOSED("closed");
	
	private String name;
	
	private State(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
