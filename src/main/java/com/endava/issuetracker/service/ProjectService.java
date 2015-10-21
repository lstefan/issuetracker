package com.endava.issuetracker.service;

import java.util.List;
import java.util.Set;

import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.State;
import com.endava.issuetracker.domain.Version;

public interface ProjectService {

	Project findProject(Long id);

	Iterable<Project> findProjects();

	Project updateProject(Project project);

	void deleteProject(Long id);
	
	List<Project> findRecentProjects();	

	Set<Version> findVersionsForProject(String project);

	List<Project> findTopFiveProjects();

	Object findIfBookmarked(String projectId, String userId);

	List<Project> findBookmarkedProjects(String userId);

	List<Project> findMyProjects(String userId);

}
