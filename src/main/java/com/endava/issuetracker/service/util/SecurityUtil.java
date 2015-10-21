package com.endava.issuetracker.service.util;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.endava.issuetracker.domain.User;
import com.endava.issuetracker.service.impl.SocialUserDetailsServiceImpl;
import com.endava.issuetracker.service.impl.UserDetailsImpl;

public class SecurityUtil {

    //private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    public static void logInUser(User user) {
        //LOGGER.info("Logging in user: {}", user);

    	UserDetailsImpl userDetails = UserDetailsImpl.getBuilder()
                .firstName(user.getFirstname())
                .id(user.getId())
                .lastName(user.getLastname())
                .password(user.getPassword())
//                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();
        //LOGGER.debug("Logging in principal: {}", userDetails);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //LOGGER.info("User: {} has been logged in.", userDetails);
    }
}
