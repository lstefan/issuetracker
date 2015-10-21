package com.endava.issuetracker.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.issuetracker.domain.Issue;
import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.State;
import com.endava.issuetracker.domain.Version;
import com.endava.issuetracker.repository.IssueRepository;
import com.endava.issuetracker.repository.ProjectRepository;
import com.endava.issuetracker.service.ProjectService;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Override
	public Project findProject(Long id) {
		return projectRepository.findOne(id);
	}

	@Override
	public Iterable<Project> findProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> findRecentProjects() {
		List<Project> projects = projectRepository
				.findFirst10ByOrderByCreatedDesc();
		return projects;
	}

	public Set<Version> findVersionsForProject(String project) {
		return projectRepository.findByProjectName(project);
	}

	public List<Project> findTopFiveProjects() {
		List<Project> projects = projectRepository.findTopFiveProjects();
		return projects;

	}

	public Object findIfBookmarked(String projectId, String userId) {
		Object obj = projectRepository.findIfBookmarked(projectId, userId);
		return obj;
	}

	public List<Project> findBookmarkedProjects(String userId) {
		return projectRepository.findBookmarkedProjects(userId);
	}

	public List<Project> findMyProjects(String userId) {
		return projectRepository.findMyProjects(userId);
	}

	public void deleteProject(Long id) {
		projectRepository.delete(id);
	}
}
