package com.endava.issuetracker.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.endava.issuetracker.domain.Category;
import com.endava.issuetracker.domain.Issue;
import com.endava.issuetracker.domain.Priority;
import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.ProjectAndVersion;
import com.endava.issuetracker.domain.Severity;
import com.endava.issuetracker.domain.State;
import com.endava.issuetracker.domain.User;
import com.endava.issuetracker.domain.Version;
import com.endava.issuetracker.service.IssueService;
import com.endava.issuetracker.service.ProjectService;
import com.endava.issuetracker.service.UserService;
import com.endava.issuetracker.service.VersionService;

/**
 * 
 * @author Livia Stefan
 * Handles requests for project(s) views
 */

@Controller
public class ProjectController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	
	@Autowired
	private VersionService versionService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/user/projects", method = RequestMethod.GET)
	public String displayProjects(Model model) {
		Iterable<Project> projectsList = projectService.findProjects();
		model.addAttribute("projectsList", projectsList);
		return "user/projects";
	}
	
	@RequestMapping(value = "/projects/viewBookmarked", method = RequestMethod.GET)
	public String displayBookmarkedProjects(Model model) {
		User user = userService.getLoggedInUser();
		Iterable<Project> projectsList = projectService.findBookmarkedProjects(user.getId().toString());
		model.addAttribute("projectsList", projectsList);
		return "user/projects";
	}	
	
	@RequestMapping(value = "/projects/viewMyProjects", method = RequestMethod.GET)
	public String displayMyProjects(Model model) {
		User user = userService.getLoggedInUser();
		Iterable<Project> projectsList = projectService.findMyProjects(user.getId().toString());
		model.addAttribute("projectsList", projectsList);
		return "user/projects";
	}	
	
	@RequestMapping(value = "/user/createProject", method = RequestMethod.GET)
	public String addProject(Model model) {

		Project newProject = new Project();
		Version newVersion = new Version();
		
		ProjectAndVersion pv = new ProjectAndVersion();
		pv.setProject(newProject);
		pv.setVersion(newVersion);
		
		model.addAttribute("projectVersion", pv);
		
		return "user/createProject";
	}
	
	@RequestMapping(value="/user/createProject", method = RequestMethod.POST)
	public String addProject(@ModelAttribute ProjectAndVersion pv, @RequestParam Map<String, String> params) {
		
		if(params != null && params.containsKey("save")) {

			Project project = pv.getProject();
			Version version = pv.getVersion();	
			
			long timeNow = Calendar.getInstance().getTimeInMillis();
			Timestamp ts = new java.sql.Timestamp(timeNow);
			project.setCreated(ts);
			project.setModified(ts);
			
			User owner = userService.getLoggedInUser();
			project.setOwner(owner); 
			
			Set<Version> versions = new HashSet<Version>();
			versions.add(version);
			project.setVersions(versions);
			
			projectService.updateProject(project);
					
			version.setProject(project);
			versionService.saveVersion(version);
			
		}

		return "redirect:/user/projects";
	}	
	
	@RequestMapping(value ="/projects/{projectId}/addIssue", method = RequestMethod.GET)
	public String addIssueToProject(@PathVariable("projectId") long projectId, Model model) {
		Issue newIssue = new Issue();
		ArrayList<Project> projectsList = new ArrayList<Project>();
		Project selectedProject = projectService.findProject(projectId);
		Set<Version> versions = selectedProject.getVersions();
		projectsList.add(selectedProject);
		newIssue.setProject(selectedProject);
		
		model.addAttribute("selectedProject", selectedProject);
		model.addAttribute("selectedProjectName", selectedProject.getProjectName());
		model.addAttribute("projectsList", projectsList);
		model.addAttribute("issue", newIssue);
		model.addAttribute("categoryList", Category.values()); 
		model.addAttribute("severityList", Severity.values()); 
		model.addAttribute("priorityList", Priority.values()); 		
		model.addAttribute("versionList", versions);
		
		return "user/addIssueForProject";
	}
	
	@RequestMapping(value ="/projects/{projectId}/addIssue", method = RequestMethod.POST)
	public String addIssueToProject(@ModelAttribute Issue issue, @RequestParam Map<String, String> params) {
		if(params != null && params.containsKey("save")) {
			if(params.get("project.id") != null) {
				Project selectedProject = projectService.findProject(Long.valueOf(params.get("project.id")));
				issue.setProject(selectedProject);
				issue.setAssigned(selectedProject.getOwner());
					
			}
			
/*			String versionName = params.get("openOnVersion.name");
			if(versionName != null && !versionName.equals("--Please Select a project")) {
				Version v = versionService.findVersion(versionName);
				issue.setOpenOnVersion(v);
				
			}*/
			
			User reporter = userService.getLoggedInUser();
			issue.setCreatedBy(reporter);
			issue.setModifiedBy(reporter);
			issue.setState(State.OPEN);
			
			long timeNow = Calendar.getInstance().getTimeInMillis();
			Timestamp ts = new java.sql.Timestamp(timeNow);
			issue.setCreated(ts);
			issue.setModified(ts);
			
			issueService.saveIssue(issue);	
			
		}

		return "redirect:/user/issues";
	}	
	
/*	@RequestMapping(value ="/projects/{projectId}/addToFavorites", method = RequestMethod.GET)
	public String addToFav(@PathVariable("projectId") long projectId, Model model) {
		Project project = projectService.findProject(projectId);
		User loggedInUser = userService.getLoggedInUser();
		List<User> users = project.getAssignedUsers();
		List<Project> projects = loggedInUser.getAssignedProjects();
		if(users == null) {
			users = new ArrayList<User>();
		}
		if(projects == null) {
			projects = new ArrayList<Project>();
		}
		users.add(loggedInUser);
		project.setAssignedUsers(users);
		
		projects.add(project);
		loggedInUser.setAssignedProjects(projects);
		
		projectService.updateProject(project);
		userService.saveUser(loggedInUser);
		
		return "redirect:/user/projects";
	}*/
	
	@RequestMapping(value ="/projects/{projectId}/bookmark", method = RequestMethod.GET)
	public String bookmark(@PathVariable("projectId") long projectId, Model model) {
		Project project = projectService.findProject(projectId);
		User loggedInUser = userService.getLoggedInUser();
		Set<User> users = project.getAssignedUsers();
		Set<Project> projects = loggedInUser.getAssignedProjects();
		if(users == null) {
			users = new HashSet<User>();
		}
		if(projects == null) {
			projects = new HashSet<Project>();
		}
		users.add(loggedInUser);
		project.setAssignedUsers(users);
		
		projects.add(project);
		loggedInUser.setAssignedProjects(projects);
		
		projectService.updateProject(project);
		userService.saveUser(loggedInUser);
 
		return "redirect:/projects/{projectId}";
	}
	
	@RequestMapping(value ="/projects/{projectId}/removeFromBookmark", method = RequestMethod.GET)
	public String removeFromBookmark(@PathVariable("projectId") long projectId, Model model) {
		Project project = projectService.findProject(projectId);
		User loggedInUser = userService.getLoggedInUser();
		Set<User> users = project.getAssignedUsers();
		Set<Project> projects = loggedInUser.getAssignedProjects();

		users.remove(loggedInUser);
		project.setAssignedUsers(users);
		
		projects.remove(project);
		loggedInUser.setAssignedProjects(projects);
		
		projectService.updateProject(project);
		userService.saveUser(loggedInUser);
 
		return "redirect:/projects/{projectId}";		
	}
	
	@RequestMapping(value ="/projects/{projectId}/editProject", method = RequestMethod.GET)
	public String editProject(@PathVariable("projectId") long projectId, Model model) {
		
        Project editedProject = projectService.findProject(projectId);
        Version newVersion = new Version();

		Iterable<Version> versionsList = editedProject.getVersions();
		model.addAttribute("versionsList", versionsList);
		
      
        ProjectAndVersion pv = new ProjectAndVersion();
        pv.setProject(editedProject);
        pv.setVersion(newVersion);
        

        model.addAttribute("editedProjectVersion", pv);
        
		return "user/editProject";
	}

	@RequestMapping(value ="/projects/{projectId}/editProject", method = RequestMethod.POST)
	public String editProject(@ModelAttribute ProjectAndVersion pv, @PathVariable("projectId") long projectId, @RequestParam Map<String, String> params) {
		if(params != null && params.containsKey("save")) {
		
			Project newProject = projectService.findProject(projectId);
			Project editedProject = pv.getProject();
			
			newProject.setProjectName(editedProject.getProjectName());
			newProject.setDescription(editedProject.getDescription());
			newProject.setReleaseNotes(editedProject.getReleaseNotes());
			newProject.setLocation(editedProject.getLocation());
						
			long timeNow = Calendar.getInstance().getTimeInMillis();
			Timestamp ts = new java.sql.Timestamp(timeNow);
			newProject.setModified(ts);
			
			Version version = pv.getVersion();
		
			Set<Version> versions = newProject.getVersions();
			
			if(version.getName() != "" && versions != null) {
				versions.add(version);
				newProject.setVersions(versions);		
				version.setProject(newProject);
				versionService.saveVersion(version);
			}
			
			projectService.updateProject(newProject);
					
		}

		return "redirect:/user/projects";
		
	}
	
	@RequestMapping(value ="/projects/{projectId}", method = RequestMethod.GET)
    public ModelAndView showProject(@PathVariable("projectId") long projectId) {
		Project project = this.projectService.findProject(projectId);
		Set<Version> versionsList = project.getVersions();
		User loggedInUser = userService.getLoggedInUser();
		ModelAndView mav = new ModelAndView("user/projectDetails");
		
		//find if bookmarked
		if(projectService.findIfBookmarked(project.getId().toString(), loggedInUser.getId().toString()) != null) { //
			mav.addObject("bookmarked",true);
		} else {
			mav.addObject("bookmarked",false);
		}
        
        mav.addObject(project);
        mav.addObject("versionsList", versionsList);
        return mav;
    }	
	
	
	@RequestMapping(value ="/projects/{projectId2}/deleteProject", method = RequestMethod.GET)	
	public String deleteProject(@PathVariable("projectId2") long projectId2, Model model) {
		projectService.deleteProject(projectId2);
		return "forward:/user/projects";
	}
	
	
	

	

}
