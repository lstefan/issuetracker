package com.endava.issuetracker.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.Version;

public interface ProjectRepository extends
		PagingAndSortingRepository<Project, Long> {

	List<Project> findFirst10ByOrderByCreatedDesc();

	@Query(value = "select * from project where USER_ID=(?1);", nativeQuery = true)
	List<Project> findMyProjects(String userId);

	Set<Version> findByProjectName(String projectName);

	@Query(value = "select project.*, count(issue.id) as noOfIssues from issue "
			+ "left join project "
			+ "on issue.PROJECT_ID=project.id "
			+ "group by id " + "order by noOfIssues DESC " + "limit 5;", nativeQuery = true)
	List<Project> findTopFiveProjects();

	@Query(value = "select * from project_users where PROJECT_ID=(?1) and USER_ID=(?2);", nativeQuery = true)
	Object findIfBookmarked(String projectId, String userId);

	@Query(value = "select project.* from project_users "
			+ "left join project " + "on project_users.PROJECT_ID=project.id "
			+ "where project_users.USER_ID=(?1);", nativeQuery = true)
	List<Project> findBookmarkedProjects(String userId);

}
