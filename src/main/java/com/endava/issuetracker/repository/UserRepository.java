package com.endava.issuetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

/*	@Query("select u from User u where lower(u.email) = lower(:email)")
	User findByEmail(@Param("email")String email);*/
    public User findByEmail(String email);
    
	@Query(value="select user.*, count(issue.id) as noOfIssues from issue " +
			"left join user " +
			"on issue.CREATED_BY=user.id "+
			"group by id "+
			"order by noOfIssues DESC "+
			"limit 5;", nativeQuery = true)
	List<User> findTopFiveUsers();
}
