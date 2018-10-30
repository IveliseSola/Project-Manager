package com.projectmanager.pm.controller;

import com.projectmanager.pm.model.Developer;
import com.projectmanager.pm.model.Project;
import com.projectmanager.pm.repository.DeveloperRepository;
import com.projectmanager.pm.repository.ProjectRepository;
import com.projectmanager.pm.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/projects")
@RestController
public class ProjectController {

	@Autowired
    ProjectRepository projectRepository;
	
	@Autowired
	DeveloperRepository developerRepository;
	
	Project project;

    @GetMapping("/")
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }
    
    @GetMapping("/{Id}")
    public Optional<Project> getAProject(@PathVariable Long Id) {
        return projectRepository.findById(Id);
    }
    
    @PostMapping("/")
    public Project createProject(@Valid @RequestBody Project project, @RequestParam(value="developersID") Set<Long> developersID) {
    	
    	Set<Developer> developers = new HashSet<>();
    	Set<Project> projects = new HashSet<>();
    	
    	projects.add(this.project);
    	
    	for (Long id : developersID) {	
    		
	    	Developer dev = developerRepository.findThatGuy(id);
	        dev.setProjects(projects);
	        developers.add(dev);
	        developerRepository.save(dev);
    	}
    	project.setDevelopers(developers);
        return projectRepository.save(project);
    }
  
   
   @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long projectId, 
    		                     @RequestParam(value="developersID") Set<Long> developersID, 
    		                     @Valid @RequestBody Project projectRequest) {
	   Set<Developer> developers = new HashSet<>();
	   
	   for (Long id : developersID) { developers.add(developerRepository.findThatGuy(id)); }
	   
        return projectRepository.findById(projectId).map(project -> {
            project.setName(projectRequest.getName());
            project.setDescription(projectRequest.getDescription());
            project.setCompletion(projectRequest.getCompletion());
            project.setDeadline(projectRequest.getDeadline());
            project.setGithubURL(projectRequest.getGithubURL());
            project.setDevelopers(developers);
          
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("ProjectId " + projectId + " not found"));
    }

    
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        return projectRepository.findById(projectId).map(project -> {
            projectRepository.delete(project);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ProjectId " + projectId + " not found"));
    }
    
    
    // Get All Developers By Project ID
    
    @GetMapping("/{projectId}/developers")
    public List<Object[]> getAllDeveloperByProjectId(@PathVariable (value = "projectId") Long projectId) {
        return projectRepository.developersByProject(projectId);
    }

}

