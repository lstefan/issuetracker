package com.endava.issuetracker.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.issuetracker.domain.Issue;
import com.endava.issuetracker.domain.Version;
import com.endava.issuetracker.repository.VersionRepository;
import com.endava.issuetracker.service.VersionService;

@Service
@Transactional
public class VersionServiceImpl implements VersionService {

	@Autowired
	private VersionRepository versionRepository;
	
	public Version findVersion(String name, String id) {
		return versionRepository.findByName(name, id);
	}
	
	public List<Version> findAllVersions() {
		List<Version> versionsList = new ArrayList<Version>();
		Iterable<Version> findAll = versionRepository.findAll();
		
		Iterator<Version> iterator = findAll.iterator();
		while(iterator.hasNext()) {
			versionsList.add(iterator.next());
		}
		
		return versionsList;
	}
	
	public Version saveVersion(Version version) {
		return versionRepository.save(version);
	}
}
