package com.endava.issuetracker.service;

import java.util.List;

import com.endava.issuetracker.domain.Issue;
import com.endava.issuetracker.domain.State;

public interface IssueService {

	Issue findIssue(Long id);
	
	List<Issue> findAllIssues();
	
	Issue saveIssue(Issue issue);
	
	void deleteIssue(Long id);
	
	Issue updateIssueState(Long id, State newState);
	
	Issue reopenIssue(Long id);
	
	List<Issue> findReportedIssues(String userId);
	
	List<Issue> findAssignedIssues(String userId);	
	
	String countIssuesPerState(String state, String projectId);
	
	String countIssuesPerPriority(String priority, String projectId);
	
	String countIssuesPerSeverity(String severity, String projectId);	
	
	String countIssuesPerResolution(String resolution, String projectId);
	
	String countAllIssuesPerState(String state, String assigned);
	
	String countAllIssuesPerPriority(String priority, String assigned);
	
	String countAllIssuesPerSeverity(String severity, String assigned);	
	
	String countAllIssuesPerResolution(String resolution, String assigned);	
	
	int[] openIssuesPerMonth(String state, String year, String projectId);
	
	int[] openIssuesPerMonthAllProj(String state, String year, String owner);
	
	int[] openIssuesPerMonthFollowedProj(String state, String year, String owner);
	
	String countAllFollowedIssuesPerState(String state, String assigned);
	
	String countAllFollowedIssuesPerPriority(String priority, String assigned);
	
	String countAllFollowedIssuesPerSeverity(String severity, String assigned);		
}
