package com.endava.issuetracker.service;

import java.util.List;

import com.endava.issuetracker.domain.Version;

public interface VersionService {
	Version findVersion(String name, String project_id);
	
	List<Version> findAllVersions();
	
	Version saveVersion(Version version);
}
