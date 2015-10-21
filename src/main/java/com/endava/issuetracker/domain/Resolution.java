package com.endava.issuetracker.domain;

public enum Resolution {
	FIXED("fixed"),
	WONTFIX("won't fix"),
	NOTREPRODUCIBLE("not reproducible"),
	DUPLICATE("duplicate"),
	INVALID("invalid"),
	ASDESIGNED("as designed"),
	EXTERNAL("external");
	
	private String name;
	
	private Resolution(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}	
}
