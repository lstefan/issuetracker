package com.endava.issuetracker.domain;

public enum Severity {
	CRITICAL("critical"),
	MAJOR("major"),
	MODERATE("moderate"),
	MINOR("minor"),
	COSMETIC("cosmetic");
	
	private String name;
	
	private Severity(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
