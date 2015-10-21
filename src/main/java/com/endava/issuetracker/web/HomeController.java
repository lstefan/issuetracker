package com.endava.issuetracker.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.User;
import com.endava.issuetracker.service.ProjectService;
import com.endava.issuetracker.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale) {
		logger.info("Client locale is: " + locale.getDisplayLanguage()); 
		
		return "forward:/user/homepage";
	}

	@RequestMapping(value = "/user/homepage", method = RequestMethod.GET)
	public String homePage(Model model) {
		Iterable<Project> projectsList = projectService.findRecentProjects();
		Iterable<Project> topProjectsList = projectService.findTopFiveProjects();
		Iterable<User> topUsers = userService.returnTopFiveUsers();
		model.addAttribute("projectsList", projectsList);
		model.addAttribute("topProjectsList", topProjectsList);
		model.addAttribute("topUsersList",topUsers);
		return "/user/home";
	}

}
