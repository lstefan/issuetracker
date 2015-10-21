package com.endava.issuetracker.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.issuetracker.domain.User;
import com.endava.issuetracker.repository.UserRepository;

/**
 * 
 * @author Livia Stefan
 *
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	//@Autowired
	private UserRepository userRepository;
	
    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);; 

		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
        UserDetailsImpl principal = UserDetailsImpl.getBuilder()
                .firstName(user.getFirstname())
                .id(user.getId())
                .lastName(user.getLastname())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();
		
		return principal;
	}
	


}
