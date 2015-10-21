package com.endava.issuetracker.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.endava.issuetracker.domain.Issue;
import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.User;
import com.endava.issuetracker.service.IssueService;
import com.endava.issuetracker.service.ProjectService;
import com.endava.issuetracker.service.UserService;
import com.endava.issuetracker.service.VersionService;

@Controller
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	
	@Autowired
	private VersionService versionService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/dashboard/{type}", method = RequestMethod.GET)
	public String displayDashboard(@PathVariable("type") String type, Model model) {
		User loggedIn = userService.getLoggedInUser();
		Iterable<Project> projectsList;
		if(type.equals("mine")) {
			projectsList = projectService.findMyProjects(loggedIn.getId().toString());
		} else {
			projectsList = projectService.findBookmarkedProjects(loggedIn.getId().toString());
		}

		List<String> projectNames = new ArrayList<String>();
		for(Project p : projectsList) {
			projectNames.add(p.getProjectName());
		}
		model.addAttribute("projectNames", projectNames);
		model.addAttribute("projectsList", projectsList);
		model.addAttribute("type", type);
		return "forward:/dashboard/project/all/{type}";
	}
	
	@RequestMapping(value ="/dashboard/project/{projectId}/{type}", method = RequestMethod.GET)
    public ModelAndView showDashboardPerProject(@PathVariable("projectId") long projectId, @PathVariable("type") String type) {

		Project project = this.projectService.findProject(projectId);
		User loggedIn = userService.getLoggedInUser();
		Iterable<Project> projectsList;
		if(type.equals("mine")) {
			projectsList = projectService.findMyProjects(loggedIn.getId().toString());
		} else {
			projectsList = projectService.findBookmarkedProjects(loggedIn.getId().toString());
		}
		
		//count issues by state
		String openIssues = issueService.countIssuesPerState("OPEN", String.valueOf(projectId));
		String closedIssues = issueService.countIssuesPerState("CLOSED", String.valueOf(projectId));
		String inProgressIssues = issueService.countIssuesPerState("IN_PROGRESS", String.valueOf(projectId));
		String resolvedIssues = issueService.countIssuesPerState("RESOLVED", String.valueOf(projectId));
		
		//count issues by priority
		String lowPriorityIssues = issueService.countIssuesPerPriority("LOW", String.valueOf(projectId));
		String mediumPriorityIssues = issueService.countIssuesPerPriority("MEDIUM", String.valueOf(projectId));
		String highPriorityIssues = issueService.countIssuesPerPriority("HIGH", String.valueOf(projectId));
		
		//count issues by severity
		String criticalIssues = issueService.countIssuesPerSeverity("CRITICAL", String.valueOf(projectId));
		String majorIssues = issueService.countIssuesPerSeverity("MAJOR", String.valueOf(projectId));
		String moderateIssues = issueService.countIssuesPerSeverity("MODERATE", String.valueOf(projectId));
		String minorIssues = issueService.countIssuesPerSeverity("MINOR", String.valueOf(projectId));
		String cosmeticIssues = issueService.countIssuesPerSeverity("COSMETIC", String.valueOf(projectId));
		
		Calendar now = Calendar.getInstance();
		int yearInt; 
		String yearInString; 
		
		
		yearInt= now.get(Calendar.YEAR);
		yearInString = String.valueOf(yearInt);
	

		int[] openIssuesArray = issueService.openIssuesPerMonth("OPEN", yearInString, String.valueOf(projectId));
		int[] closedIssuesArray = issueService.openIssuesPerMonth("CLOSED", yearInString, String.valueOf(projectId));
		
		ModelAndView mav = new ModelAndView("/user/projectDashboard");
		
		mav.addObject("projectsList", projectsList);
		mav.addObject("open", openIssues);
		mav.addObject("closed", closedIssues);
		mav.addObject("inProgress", inProgressIssues);
		mav.addObject("resolved", resolvedIssues);
		mav.addObject("low", lowPriorityIssues);
		mav.addObject("medium", mediumPriorityIssues);
		mav.addObject("high", highPriorityIssues);
		mav.addObject("critical", criticalIssues);
		mav.addObject("major", majorIssues);
		mav.addObject("moderate", moderateIssues);
		mav.addObject("minor",minorIssues);
		mav.addObject("cosmetic", cosmeticIssues);
        mav.addObject(project);
        mav.addObject("x",openIssuesArray);
        mav.addObject("y", closedIssuesArray);
        mav.addObject("type", type);
        return mav;
    }	
	
	@RequestMapping(value ="/dashboard/{projectId}/{year}/{type}", method = RequestMethod.GET)
	public ModelAndView showDashboardPerProjectCurrentYear(@PathVariable("projectId") long projectId, @PathVariable("year") String year, @PathVariable("type") String type) {

		Project project = this.projectService.findProject(projectId);
		User loggedIn = userService.getLoggedInUser();
		Iterable<Project> projectsList;
		if(type.equals("mine")) {
			projectsList = projectService.findMyProjects(loggedIn.getId().toString());
		} else {
			projectsList = projectService.findBookmarkedProjects(loggedIn.getId().toString());
		}
		
		//count issues by state
		String openIssues = issueService.countIssuesPerState("OPEN", String.valueOf(projectId));
		String closedIssues = issueService.countIssuesPerState("CLOSED", String.valueOf(projectId));
		String inProgressIssues = issueService.countIssuesPerState("IN_PROGRESS", String.valueOf(projectId));
		String resolvedIssues = issueService.countIssuesPerState("RESOLVED", String.valueOf(projectId));
		
		//count issues by priority
		String lowPriorityIssues = issueService.countIssuesPerPriority("LOW", String.valueOf(projectId));
		String mediumPriorityIssues = issueService.countIssuesPerPriority("MEDIUM", String.valueOf(projectId));
		String highPriorityIssues = issueService.countIssuesPerPriority("HIGH", String.valueOf(projectId));
		
		//count issues by severity
		String criticalIssues = issueService.countIssuesPerSeverity("CRITICAL", String.valueOf(projectId));
		String majorIssues = issueService.countIssuesPerSeverity("MAJOR", String.valueOf(projectId));
		String moderateIssues = issueService.countIssuesPerSeverity("MODERATE", String.valueOf(projectId));
		String minorIssues = issueService.countIssuesPerSeverity("MINOR", String.valueOf(projectId));
		String cosmeticIssues = issueService.countIssuesPerSeverity("COSMETIC", String.valueOf(projectId));
		
		
		Calendar now = Calendar.getInstance();
		int yearInt; 
		String yearInString; 
		
		if(year.equals("current")) {
			yearInt= now.get(Calendar.YEAR);
			yearInString = String.valueOf(yearInt);
		} else {
			yearInt = now.get(Calendar.YEAR) - 1;
			yearInString = String.valueOf(yearInt);
		}
		
		int[] openIssuesArray = issueService.openIssuesPerMonth("OPEN", yearInString, String.valueOf(projectId));
		int[] closedIssuesArray = issueService.openIssuesPerMonth("CLOSED", yearInString, String.valueOf(projectId));
		
		ModelAndView mav = new ModelAndView("/user/projectDashboard");
		
		mav.addObject("projectsList", projectsList);
		mav.addObject("open", openIssues);
		mav.addObject("closed", closedIssues);
		mav.addObject("inProgress", inProgressIssues);
		mav.addObject("resolved", resolvedIssues);
		mav.addObject("low", lowPriorityIssues);
		mav.addObject("medium", mediumPriorityIssues);
		mav.addObject("high", highPriorityIssues);
		mav.addObject("critical", criticalIssues);
		mav.addObject("major", majorIssues);
		mav.addObject("moderate", moderateIssues);
		mav.addObject("minor",minorIssues);
		mav.addObject("cosmetic", cosmeticIssues);
        mav.addObject(project);
        mav.addObject("x",openIssuesArray);
        mav.addObject("y", closedIssuesArray);
        mav.addObject("type",type);
        return mav;		
	}
	
	
	@RequestMapping(value ="/dashboard/project/all/{type}", method = RequestMethod.GET)
    public ModelAndView showDashboardAllProjects(@PathVariable("type") String type) {

		User loggedIn = userService.getLoggedInUser();
		Iterable<Project> projectsList;
		if(type.equals("mine")) {
			projectsList = projectService.findMyProjects(loggedIn.getId().toString());
		} else {
			projectsList = projectService.findBookmarkedProjects(loggedIn.getId().toString());
		}
		
		//count issues by state
		String openIssues, closedIssues, inProgressIssues, resolvedIssues;
		
		if(type.equals("mine")) {
		 openIssues = issueService.countAllIssuesPerState("OPEN", String.valueOf(loggedIn.getId()));
		 closedIssues = issueService.countAllIssuesPerState("CLOSED", String.valueOf(loggedIn.getId()));
		 inProgressIssues = issueService.countAllIssuesPerState("IN_PROGRESS", String.valueOf(loggedIn.getId()));
		 resolvedIssues = issueService.countAllIssuesPerState("RESOLVED", String.valueOf(loggedIn.getId()));
		} 
		else {
			 openIssues = issueService.countAllFollowedIssuesPerState("OPEN", String.valueOf(loggedIn.getId()));
			 closedIssues = issueService.countAllFollowedIssuesPerState("CLOSED", String.valueOf(loggedIn.getId()));
			 inProgressIssues = issueService.countAllFollowedIssuesPerState("IN_PROGRESS", String.valueOf(loggedIn.getId()));
			 resolvedIssues = issueService.countAllFollowedIssuesPerState("RESOLVED", String.valueOf(loggedIn.getId()));
		}
		
		//count issues by priority
		String lowPriorityIssues, mediumPriorityIssues, highPriorityIssues;
		if(type.equals("mine")) {
		 lowPriorityIssues = issueService.countAllIssuesPerPriority("LOW", String.valueOf(loggedIn.getId()));
		 mediumPriorityIssues = issueService.countAllIssuesPerPriority("MEDIUM", String.valueOf(loggedIn.getId()));
		 highPriorityIssues = issueService.countAllIssuesPerPriority("HIGH", String.valueOf(loggedIn.getId()));
		}
		else {
			 lowPriorityIssues = issueService.countAllFollowedIssuesPerPriority("LOW", String.valueOf(loggedIn.getId()));
			 mediumPriorityIssues = issueService.countAllFollowedIssuesPerPriority("MEDIUM", String.valueOf(loggedIn.getId()));
			 highPriorityIssues = issueService.countAllFollowedIssuesPerPriority("HIGH", String.valueOf(loggedIn.getId()));
		}
		
		//count issues by severity
		String criticalIssues, majorIssues, moderateIssues, minorIssues, cosmeticIssues;
		if(type.equals("mine")) {
			 criticalIssues = issueService.countAllIssuesPerSeverity("CRITICAL", String.valueOf(loggedIn.getId()));
			 majorIssues = issueService.countAllIssuesPerSeverity("MAJOR", String.valueOf(loggedIn.getId()));
			 moderateIssues = issueService.countAllIssuesPerSeverity("MODERATE", String.valueOf(loggedIn.getId()));
			 minorIssues = issueService.countAllIssuesPerSeverity("MINOR", String.valueOf(loggedIn.getId()));
			 cosmeticIssues = issueService.countAllIssuesPerSeverity("COSMETIC", String.valueOf(loggedIn.getId()));			
		}
		else {
			 criticalIssues = issueService.countAllFollowedIssuesPerSeverity("CRITICAL", String.valueOf(loggedIn.getId()));
			 majorIssues = issueService.countAllFollowedIssuesPerSeverity("MAJOR", String.valueOf(loggedIn.getId()));
			 moderateIssues = issueService.countAllFollowedIssuesPerSeverity("MODERATE", String.valueOf(loggedIn.getId()));
			 minorIssues = issueService.countAllFollowedIssuesPerSeverity("MINOR", String.valueOf(loggedIn.getId()));
			 cosmeticIssues = issueService.countAllFollowedIssuesPerSeverity("COSMETIC", String.valueOf(loggedIn.getId()));
		}

		
		Calendar now = Calendar.getInstance();
		int yearInt; 
		String yearInString; 
		
		yearInt= now.get(Calendar.YEAR);
		yearInString = String.valueOf(yearInt);
		
		int[] openIssuesArray;
		int[] closedIssuesArray;
		
		if(type.equals("mine")) {
			openIssuesArray = issueService.openIssuesPerMonthAllProj("OPEN", yearInString, String.valueOf(loggedIn.getId().toString()));
			closedIssuesArray = issueService.openIssuesPerMonthAllProj("CLOSED", yearInString, String.valueOf(loggedIn.getId().toString()));	
		}
		else {
			openIssuesArray = issueService.openIssuesPerMonthFollowedProj("OPEN", yearInString, String.valueOf(loggedIn.getId().toString()));
			closedIssuesArray = issueService.openIssuesPerMonthFollowedProj("CLOSED", yearInString, String.valueOf(loggedIn.getId().toString()));			
		}
	

		
		ModelAndView mav = new ModelAndView("/user/projectDashboard");
		
		mav.addObject("projectsList", projectsList);
		mav.addObject("open", openIssues);
		mav.addObject("closed", closedIssues);
		mav.addObject("inProgress", inProgressIssues);
		mav.addObject("resolved", resolvedIssues);
		mav.addObject("low", lowPriorityIssues);
		mav.addObject("medium", mediumPriorityIssues);
		mav.addObject("high", highPriorityIssues);
		mav.addObject("critical", criticalIssues);
		mav.addObject("major", majorIssues);
		mav.addObject("moderate", moderateIssues);
		mav.addObject("minor",minorIssues);
		mav.addObject("cosmetic", cosmeticIssues);
		mav.addObject("type", type);
        //mav.addObject(project);
        mav.addObject("x",openIssuesArray);
        mav.addObject("y", closedIssuesArray);
        return mav;
    }		
	
	@RequestMapping(value ="/dashboard/project/all/{year}/{type}", method = RequestMethod.GET)
    public ModelAndView showDashboardAllProjectsPerYear(@PathVariable("year") String year, @PathVariable("type") String type) {
	
		Calendar now = Calendar.getInstance();
		int yearInt; 
		String yearInString; 
		
		if(year.equals("current")) {
			yearInt= now.get(Calendar.YEAR);
			yearInString = String.valueOf(yearInt);
		} else {
			yearInt = now.get(Calendar.YEAR) - 1;
			yearInString = String.valueOf(yearInt);
		}
		
		User loggedIn = userService.getLoggedInUser();
		Iterable<Project> projectsList;
		if(type.equals("mine")) {
			projectsList = projectService.findMyProjects(loggedIn.getId().toString());
		} else {
			projectsList = projectService.findBookmarkedProjects(loggedIn.getId().toString());
		}
		
		//count issues by state
		String openIssues, closedIssues, inProgressIssues, resolvedIssues;
		
		if(type.equals("mine")) {
		 openIssues = issueService.countAllIssuesPerState("OPEN", String.valueOf(loggedIn.getId()));
		 closedIssues = issueService.countAllIssuesPerState("CLOSED", String.valueOf(loggedIn.getId()));
		 inProgressIssues = issueService.countAllIssuesPerState("IN_PROGRESS", String.valueOf(loggedIn.getId()));
		 resolvedIssues = issueService.countAllIssuesPerState("RESOLVED", String.valueOf(loggedIn.getId()));
		} 
		else {
			 openIssues = issueService.countAllFollowedIssuesPerState("OPEN", String.valueOf(loggedIn.getId()));
			 closedIssues = issueService.countAllFollowedIssuesPerState("CLOSED", String.valueOf(loggedIn.getId()));
			 inProgressIssues = issueService.countAllFollowedIssuesPerState("IN_PROGRESS", String.valueOf(loggedIn.getId()));
			 resolvedIssues = issueService.countAllFollowedIssuesPerState("RESOLVED", String.valueOf(loggedIn.getId()));
		}
		
		//count issues by priority
		String lowPriorityIssues, mediumPriorityIssues, highPriorityIssues;
		if(type.equals("mine")) {
		 lowPriorityIssues = issueService.countAllIssuesPerPriority("LOW", String.valueOf(loggedIn.getId()));
		 mediumPriorityIssues = issueService.countAllIssuesPerPriority("MEDIUM", String.valueOf(loggedIn.getId()));
		 highPriorityIssues = issueService.countAllIssuesPerPriority("HIGH", String.valueOf(loggedIn.getId()));
		}
		else {
			 lowPriorityIssues = issueService.countAllFollowedIssuesPerPriority("LOW", String.valueOf(loggedIn.getId()));
			 mediumPriorityIssues = issueService.countAllFollowedIssuesPerPriority("MEDIUM", String.valueOf(loggedIn.getId()));
			 highPriorityIssues = issueService.countAllFollowedIssuesPerPriority("HIGH", String.valueOf(loggedIn.getId()));
		}
		
		//count issues by severity
		String criticalIssues, majorIssues, moderateIssues, minorIssues, cosmeticIssues;
		if(type.equals("mine")) {
			 criticalIssues = issueService.countAllIssuesPerSeverity("CRITICAL", String.valueOf(loggedIn.getId()));
			 majorIssues = issueService.countAllIssuesPerSeverity("MAJOR", String.valueOf(loggedIn.getId()));
			 moderateIssues = issueService.countAllIssuesPerSeverity("MODERATE", String.valueOf(loggedIn.getId()));
			 minorIssues = issueService.countAllIssuesPerSeverity("MINOR", String.valueOf(loggedIn.getId()));
			 cosmeticIssues = issueService.countAllIssuesPerSeverity("COSMETIC", String.valueOf(loggedIn.getId()));			
		}
		else {
			 criticalIssues = issueService.countAllFollowedIssuesPerSeverity("CRITICAL", String.valueOf(loggedIn.getId()));
			 majorIssues = issueService.countAllFollowedIssuesPerSeverity("MAJOR", String.valueOf(loggedIn.getId()));
			 moderateIssues = issueService.countAllFollowedIssuesPerSeverity("MODERATE", String.valueOf(loggedIn.getId()));
			 minorIssues = issueService.countAllFollowedIssuesPerSeverity("MINOR", String.valueOf(loggedIn.getId()));
			 cosmeticIssues = issueService.countAllFollowedIssuesPerSeverity("COSMETIC", String.valueOf(loggedIn.getId()));
		}
		
		int[] openIssuesArray;
		int[] closedIssuesArray;
		
		if(type.equals("mine")) {
			openIssuesArray = issueService.openIssuesPerMonthAllProj("OPEN", yearInString, String.valueOf(loggedIn.getId().toString()));
			closedIssuesArray = issueService.openIssuesPerMonthAllProj("CLOSED", yearInString, String.valueOf(loggedIn.getId().toString()));	
		}
		else {
			openIssuesArray = issueService.openIssuesPerMonthFollowedProj("OPEN", yearInString, String.valueOf(loggedIn.getId().toString()));
			closedIssuesArray = issueService.openIssuesPerMonthFollowedProj("CLOSED", yearInString, String.valueOf(loggedIn.getId().toString()));			
		}
	

		
		ModelAndView mav = new ModelAndView("/user/projectDashboard");
		
		mav.addObject("projectsList", projectsList);
		mav.addObject("open", openIssues);
		mav.addObject("closed", closedIssues);
		mav.addObject("inProgress", inProgressIssues);
		mav.addObject("resolved", resolvedIssues);
		mav.addObject("low", lowPriorityIssues);
		mav.addObject("medium", mediumPriorityIssues);
		mav.addObject("high", highPriorityIssues);
		mav.addObject("critical", criticalIssues);
		mav.addObject("major", majorIssues);
		mav.addObject("moderate", moderateIssues);
		mav.addObject("minor",minorIssues);
		mav.addObject("cosmetic", cosmeticIssues);
		mav.addObject("type", type);
        //mav.addObject(project);
        mav.addObject("x",openIssuesArray);
        mav.addObject("y", closedIssuesArray);
        return mav;		

    }		
		
}
