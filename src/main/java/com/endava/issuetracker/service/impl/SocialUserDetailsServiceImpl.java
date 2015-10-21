package com.endava.issuetracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Livia Stefan
 *
 */
@Service("socialUserDetailsService")
@Transactional
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

	//@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public SocialUserDetailsServiceImpl(UserDetailsService userDetailsService) {
		// TODO Auto-generated constructor stub
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		
		return (SocialUserDetails)userDetails;
	}

}
