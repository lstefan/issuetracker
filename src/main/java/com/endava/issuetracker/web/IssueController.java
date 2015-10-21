package com.endava.issuetracker.web;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.endava.issuetracker.domain.Category;
import com.endava.issuetracker.domain.Issue;
import com.endava.issuetracker.domain.Priority;
import com.endava.issuetracker.domain.Project;
import com.endava.issuetracker.domain.Resolution;
import com.endava.issuetracker.domain.Severity;
import com.endava.issuetracker.domain.State;
import com.endava.issuetracker.domain.User;
import com.endava.issuetracker.domain.Version;
import com.endava.issuetracker.service.IssueService;
import com.endava.issuetracker.service.ProjectService;
import com.endava.issuetracker.service.UserService;
import com.endava.issuetracker.service.VersionService;
import com.google.gson.Gson;

@Controller
public class IssueController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private UserService userService;	
	
	@Autowired
	private VersionService versionService;
	
	@RequestMapping(value = "/user/issues", method = RequestMethod.GET)
	public String displayIssues(Model model) {
		Iterable<Issue> issuesList = issueService.findAllIssues();
		model.addAttribute("issuesList", issuesList);
		
		return "user/issues";
	}	
	
	@RequestMapping(value = "/issues/viewAssigned", method = RequestMethod.GET)
	public String displayAssignedIssues(Model model) {
		User user = userService.getLoggedInUser();
		Iterable<Issue> issuesList = issueService.findAssignedIssues(user.getId().toString());
		model.addAttribute("issuesList", issuesList);
		
		return "user/issues";
	}	
	
	@RequestMapping(value = "/issues/viewReported", method = RequestMethod.GET)
	public String displayReportedIssues(Model model) {
		User user = userService.getLoggedInUser();
		Iterable<Issue> issuesList = issueService.findReportedIssues(user.getId().toString());
		model.addAttribute("issuesList", issuesList);
		
		return "user/issues";
	}		
	
	
/*    @ModelAttribute("categoryList")
    public List<Category> populateCategory()
    {
        return Arrays.asList(Category.values());
    }	*/

	@RequestMapping(value = "/user/createIssue", method = RequestMethod.GET)
	public String addIssue(Model model) {
		
		Issue newIssue = new Issue();
		
		model.addAttribute("issue", newIssue);
		model.addAttribute("categoryList", Category.values()); 
		model.addAttribute("severityList", Severity.values()); 
		model.addAttribute("priorityList", Priority.values()); 

		Iterable<Project> projectsList = projectService.findProjects();
		model.addAttribute("projectsList", projectsList);
				
		return "user/createIssue";
	}
	
	@RequestMapping(value = "/user/createIssue", method = RequestMethod.POST)
	public String addIssue(@ModelAttribute Issue issue, @RequestParam Map<String, String> params) {
		
		if(params != null && params.containsKey("save")) {
			Project selectedProject = null;
			if(params.get("project.id") != null) {
				selectedProject = projectService.findProject(Long.valueOf(params.get("project.id")));
				issue.setProject(selectedProject);
				issue.setAssigned(selectedProject.getOwner());
								
			}
			
			String versionName = params.get("openOnVersion.name");
			if(versionName != null && !versionName.equals("--Please Select a project")) {
				Version v = versionService.findVersion(versionName,selectedProject.getId().toString());
				issue.setOpenOnVersion(v);
				
			}
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
	
	@RequestMapping(value ="/issues/{issueId}/editIssue", method = RequestMethod.GET)
	public String editIssue(@PathVariable("issueId") long issueId, Model model) {
        Issue editedIssue = issueService.findIssue(issueId);       
        model.addAttribute("issue", editedIssue);     
		Iterable<Project> projectsList = projectService.findProjects();
		Iterable<Version> versionsList = versionService.findAllVersions();
		String currentVersion= editedIssue.getOpenOnVersion().getName();
		model.addAttribute("currentVersion", currentVersion);
		model.addAttribute("projectsList", projectsList);
		model.addAttribute("versionsList", versionsList);
		model.addAttribute("stateList", State.values());
		model.addAttribute("priorityList", Priority.values());
		model.addAttribute("severityList", Severity.values());
		model.addAttribute("resolutionList", Resolution.values());
		return "user/editIssue";
	}
	
	@RequestMapping(value ="/issues/{issueId}/editIssue", method = RequestMethod.POST)
	public String editIssue(@ModelAttribute Issue issue, @RequestParam Map<String, String> params) {
		if(params != null && params.containsKey("save")) {
			Issue oldIssue = issueService.findIssue(issue.getId());
			issue.setAssigned(oldIssue.getAssigned());
			issue.setCreated(oldIssue.getCreated());
			issue.setCreatedBy(oldIssue.getCreatedBy());
			//issue.setState(oldIssue.getState());
			
			
			if(params.get("project.id") != null) {
				Project selectedProject = projectService.findProject(Long.valueOf(params.get("project.id")));
				issue.setProject(selectedProject);
			}
			
			long timeNow = Calendar.getInstance().getTimeInMillis();
			Timestamp ts = new java.sql.Timestamp(timeNow);
			issue.setModified(ts);
			
			String versionName = params.get("openOnVersion.name");
			if(versionName != "" && versionName != null && !versionName.equals("--Please Select a project")) {
				Version v = versionService.findVersion(versionName, params.get("project.id"));
				issue.setOpenOnVersion(v);				
			} 
			else {
				issue.setOpenOnVersion(oldIssue.getOpenOnVersion());
			}
			

/*			String resolution = params.get("resolution");
			if(resolution != "" && resolution != null && !resolution.equals("--Please Select")) {
				issue.setResolution(Resolution.valueOf(resolution));
			}
			else {
				issue.setResolution(oldIssue.getResolution());
			}*/
			

			String currentState = issue.getState().getName();
			String fixedVersionName = params.get("fixedOnVersion.name");
			if(fixedVersionName != "" && fixedVersionName != null && !currentState.equals("open") && !currentState.equals("in progress")) {
				Version v = versionService.findVersion(fixedVersionName, params.get("project.id"));
				issue.setFixedOnVersion(v);				
			} 
			else {
				issue.setFixedOnVersion(null);
				issue.setResolution(null);
			}			
			
			User currentUser = userService.getLoggedInUser();
			issue.setModifiedBy(currentUser);
			

			issueService.saveIssue(issue);			
		}

		return "redirect:/user/issues";		
	}
	

	@RequestMapping(value ="/versions", method = RequestMethod.GET)
	public @ResponseBody
	String versionsForProject(@RequestParam(value="projectName", required= true) String project) {
		long projectId = Long.valueOf(project);

		Project projectObject = projectService.findProject(projectId);
		Set<Version> versions = projectObject.getVersions();
		Set<String> versionNames = new HashSet<String>();
		for(Version v:versions) {
			versionNames.add(v.getName());
		}
		
		String json = new Gson().toJson(versionNames);
		
		return json;
	}
	
	@RequestMapping(value ="/issues/{issueId}", method = RequestMethod.GET)
    public ModelAndView showIssue(@PathVariable("issueId") long issueId) {

		Issue issue = this.issueService.findIssue(issueId);
		
		ModelAndView mav = new ModelAndView("user/issueDetails");

        mav.addObject(issue);
        return mav;
    }	
	
	@RequestMapping(value ="/issues/{issueId2}/deleteIssue", method = RequestMethod.GET)
	public String deleteIssue(@PathVariable("issueId2") long issueId, Model model) {	
		issueService.deleteIssue(issueId);
		return "forward:/user/issues";
		
	}
}
