package com.endava.issuetracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.endava.issuetracker.domain.Version;

public interface VersionRepository extends PagingAndSortingRepository<Version, Long> {
	@Query(value = "select * from version where name=(?1) and project_id=(?2);", nativeQuery = true)
	Version findByName(String name, String id);
}
