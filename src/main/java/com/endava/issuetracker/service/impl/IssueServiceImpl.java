package com.endava.issuetracker.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.issuetracker.domain.Issue;
import com.endava.issuetracker.domain.State;
import com.endava.issuetracker.repository.IssueRepository;
import com.endava.issuetracker.service.IssueService;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueRepository issueRepository;

	@Override
	public Issue findIssue(Long id) {
		return issueRepository.findOne(id);
	}

	@Override
	public List<Issue> findAllIssues() {
		List<Issue> issuesList = new ArrayList<Issue>();
		Iterable<Issue> findAll = issueRepository.findAll();
		
		Iterator<Issue> iterator = findAll.iterator();
		while(iterator.hasNext()) {
			issuesList.add(iterator.next());
		}
		
		return issuesList;
	}
	
	public Issue saveIssue(Issue issue) {
		return issueRepository.save(issue);
	}
	
	/** 
	 * caller should set issue status: issue.setState(FIXED)
	 * @param issue
	 */
	public Issue updateIssue(Issue issue) {
		return issueRepository.save(issue);
	}
	
	public Issue updateIssueState(Long id, State newState) {
		Issue issue = issueRepository.findOne(id);
		issue.setState(newState);
		return updateIssue(issue);
	}
	
	@Override
	public Issue reopenIssue(Long id) {
		return updateIssueState(id, State.OPEN);
	}

	@Override
	public List<Issue> findReportedIssues(String userId) {
		// TODO Auto-generated method stub
		return issueRepository.findReportedIssues(userId);
	}

	@Override
	public List<Issue> findAssignedIssues(String userId) {
		// TODO Auto-generated method stub
		return issueRepository.findAssignedIssues(userId);
	}

	@Override
	public String countIssuesPerState(String state, String projectId) {
		// TODO Auto-generated method stub
		return issueRepository.countIssuesPerState(state, projectId);
	}

	@Override
	public String countIssuesPerPriority(String priority, String projectId) {
		// TODO Auto-generated method stub
		return issueRepository.countIssuesPerPriority(priority, projectId);
	}

	@Override
	public String countIssuesPerSeverity(String severity, String projectId) {
		// TODO Auto-generated method stub
		return issueRepository.countIssuesPerSeverity(severity, projectId);
	}

	@Override
	public String countIssuesPerResolution(String resolution, String projectId) {
		// TODO Auto-generated method stub
		return issueRepository.countIssuesPerResolution(resolution, projectId);
	}

	@Override
	public int[] openIssuesPerMonth(String state, String year, String projectId) {
		List<Object[]> issuesObj = issueRepository.openIssuesPerMonth(state, year, projectId);

		int[] issuesPerMonth = new int[12];
		
		for(int i = 0; i < issuesObj.size(); i++) {
			int month = (int)issuesObj.get(i)[0];
			int nr = ((BigInteger) issuesObj.get(i)[1]).intValue();
			issuesPerMonth[month - 1] = nr;			
		}
		
		return issuesPerMonth;
	}
	
	@Override
	public int[] openIssuesPerMonthAllProj(String state, String year, String owner) {
		List<Object[]> issuesObj = issueRepository.openIssuesPerMonthAllProj(state, year, owner);

		int[] issuesPerMonth = new int[12];
		
		for(int i = 0; i < issuesObj.size(); i++) {
			int month = (int)issuesObj.get(i)[0];
			int nr = ((BigInteger) issuesObj.get(i)[1]).intValue();
			issuesPerMonth[month - 1] = nr;			
		}
		
		return issuesPerMonth;
	}

	@Override
	public int[] openIssuesPerMonthFollowedProj(String state, String year, String owner) {
		List<Object[]> issuesObj = issueRepository.openIssuesPerMonthFollowedProj(state, year, owner);

		int[] issuesPerMonth = new int[12];
		
		for(int i = 0; i < issuesObj.size(); i++) {
			int month = (int)issuesObj.get(i)[0];
			int nr = ((BigInteger) issuesObj.get(i)[1]).intValue();
			issuesPerMonth[month - 1] = nr;			
		}
		
		return issuesPerMonth;
	}
	
	@Override
	public String countAllIssuesPerState(String state, String assigned) {
		// TODO Auto-generated method stub
		return issueRepository.countAllIssuesPerState(state, assigned);
	}

	@Override
	public String countAllIssuesPerPriority(String priority, String assigned) {
		// TODO Auto-generated method stub
		return issueRepository.countAllIssuesPerPriority(priority, assigned);
	}

	@Override
	public String countAllIssuesPerSeverity(String severity, String assigned) {
		// TODO Auto-generated method stub
		return issueRepository.countAllIssuesPerSeverity(severity, assigned);
	}

	@Override
	public String countAllIssuesPerResolution(String resolution, String assigned) {
		// TODO Auto-generated method stub
		return issueRepository.countAllIssuesPerResolution(resolution, assigned);
	}
	
	@Override
	public String countAllFollowedIssuesPerState(String state, String assigned) {
		// TODO Auto-generated method stub
		return issueRepository.countAllFollowedIssuesPerState(state, assigned);
	}

	@Override
	public String countAllFollowedIssuesPerPriority(String priority, String assigned) {
		// TODO Auto-generated method stub
		return issueRepository.countAllFollowedIssuesPerPriority(priority, assigned);
	}

	@Override
	public String countAllFollowedIssuesPerSeverity(String severity, String assigned) {
		// TODO Auto-generated method stub
		return issueRepository.countAllFollowedIssuesPerSeverity(severity, assigned);
	}	
	
	public void deleteIssue(Long id) {
		issueRepository.delete(id);
	}
}
