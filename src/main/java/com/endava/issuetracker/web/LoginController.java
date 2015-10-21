package com.endava.issuetracker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.endava.issuetracker.exceptions.DuplicateEmailException;
import com.endava.issuetracker.register.RegistrationForm;
import com.endava.issuetracker.service.UserService;
import com.endava.issuetracker.service.util.SocialMediaProvider;


@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    protected static final String VIEW_NAME_LOGIN_PAGE = "user/login";
    private UserService service;
    private ProviderSignInUtils provider;

    @Autowired
    public LoginController(UserService service) {
        this.service = (UserService) service;
        this.provider = new ProviderSignInUtils();
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        LOGGER.debug("Display login page.");
        return "user/login";
    }
    
}
