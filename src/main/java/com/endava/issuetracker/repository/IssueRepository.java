package com.endava.issuetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.endava.issuetracker.domain.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {
	@Query(value="select * from issue where CREATED_BY=(?1);", nativeQuery = true)	
	List<Issue> findReportedIssues(String userId);
	
	@Query(value="select * from issue where ASSIGNED=(?1);", nativeQuery = true)	
	List<Issue> findAssignedIssues(String userId);	
	
	@Query(value="select count(*) from issue where state=(?1) and project_id=(?2);", nativeQuery = true)
	String countIssuesPerState(String state, String projectId);
	
	@Query(value="select count(*) from issue where priority=(?1) and project_id=(?2);", nativeQuery = true)
	String countIssuesPerPriority(String priority, String projectId);
	
	@Query(value="select count(*) from issue where severity=(?1) and project_id=(?2);", nativeQuery = true)
	String countIssuesPerSeverity(String severity, String projectId);	
	
	@Query(value="select count(*) from issue where resolution=(?1) and project_id=(?2);", nativeQuery = true)
	String countIssuesPerResolution(String resolution, String projectId);	
	
	@Query(value="select count(*) from issue where state=(?1) and ASSIGNED=(?2);", nativeQuery = true)
	String countAllIssuesPerState(String state, String assigned);
	
	@Query(value="select count(*) from issue where priority=(?1) and ASSIGNED=(?2);", nativeQuery = true)
	String countAllIssuesPerPriority(String priority, String projectId);
	
	@Query(value="select count(*) from issue where severity=(?1) and ASSIGNED=(?2);", nativeQuery = true)
	String countAllIssuesPerSeverity(String severity, String projectId);	
	
	@Query(value="select count(*) from issue where resolution=(?1) and ASSIGNED=(?2);", nativeQuery = true)
	String countAllIssuesPerResolution(String resolution, String projectId);
	
	@Query(value="select MONTH(i.CREATED) as month, count(*) from issue i where state=(?1) and YEAR(i.created) = (?2) and PROJECT_ID = (?3) GROUP BY month ORDER BY month ASC;", nativeQuery = true)
	List<Object[]> openIssuesPerMonth(String state, String year, String projectID);
	
	@Query(value="select MONTH(i.CREATED) as month, count(*) from issue i where state=(?1) and YEAR(i.created) = (?2) and ASSIGNED = (?3) GROUP BY month ORDER BY month ASC;", nativeQuery = true)
	List<Object[]> openIssuesPerMonthAllProj(String state, String year, String owner);	
	
	@Query(value="select MONTH(i.CREATED) as month, count(*) from issue i inner join project_users on i.PROJECT_ID=project_users.PROJECT_ID where state=(?1) and YEAR(i.created) = (?2) and project_users.USER_ID=(?3) GROUP BY month ORDER BY month ASC;", nativeQuery = true)
	List<Object[]> openIssuesPerMonthFollowedProj(String state, String year, String user);	
	
	@Query(value="select count(*) from issue inner join project_users on issue.PROJECT_ID=project_users.PROJECT_ID where state=(?1) and project_users.USER_ID = (?2);", nativeQuery = true)
	String countAllFollowedIssuesPerState(String state, String user);
	
	@Query(value="select count(*) from issue inner join project_users on issue.PROJECT_ID=project_users.PROJECT_ID where priority=(?1) and project_users.USER_ID = (?2);", nativeQuery = true)
	String countAllFollowedIssuesPerPriority(String priority, String user);
	
	@Query(value="select count(*) from issue inner join project_users on issue.PROJECT_ID=project_users.PROJECT_ID where severity=(?1) and project_users.USER_ID = (?2);", nativeQuery = true)
	String countAllFollowedIssuesPerSeverity(String severity, String user);	
}
