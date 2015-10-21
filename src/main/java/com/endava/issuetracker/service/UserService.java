package com.endava.issuetracker.service;

import java.util.List;

import com.endava.issuetracker.domain.User;
import com.endava.issuetracker.exceptions.DuplicateEmailException;
import com.endava.issuetracker.register.RegistrationForm;

public interface UserService {
	 
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
    
    public User saveUser(User user);
    
    List<User> returnTopFiveUsers();
    
    User getLoggedInUser();
}
