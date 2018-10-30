package com.projectmanager.pm.controller;

import com.projectmanager.pm.model.Task;
import com.projectmanager.pm.repository.TaskRepository;
import com.projectmanager.pm.repository.DeveloperRepository;
import com.projectmanager.pm.repository.ProjectRepository;
import com.projectmanager.pm.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/projects")
@RestController
public class TaskController {
	
	@Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private DeveloperRepository developerRepository;

    @GetMapping("/{projectId}/tasks")
    public Page<Task> getAllTasksByProjectId(@PathVariable (value = "projectId") Long projectId, Pageable pageable) {
        return taskRepository.findByProjectId(projectId, pageable);
    }

    
    @PostMapping("/{projectId}/tasks")
    public Task createTask(@PathVariable (value = "projectId") Long projectId, 
    		               @Valid @RequestBody Task task,
    		               @RequestParam (value = "developerID") Long developerID ) {
    	task.setDeveloper(developerRepository.findThatGuy(developerID));
        return projectRepository.findById(projectId).map(project -> {
            task.setProject(project); 
            Set<Task> tasks = new HashSet<>();
            tasks = project.getTasks();
            tasks.add(task);
            project.setTasks(tasks);
            project.setCompletion();
            projectRepository.save(project);
            return task;
        }).orElseThrow(() -> new ResourceNotFoundException("ProjectId " + projectId + " not found"));
    }

    
    @PutMapping("/{projectId}/tasks/{taskId}")
    public Task updatetask(@PathVariable (value = "projectId") Long projectId,
                           @PathVariable (value = "taskId") Long taskId,
                           @Valid @RequestBody Task taskRequest) {
        if(!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("ProjectId " + projectId + " not found");
        }
        return taskRepository.findById(taskId).map(task -> {
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("TaskId " + taskId + "not found"));
    }
    

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable (value = "projectId") Long projectId,
                              @PathVariable (value = "taskId") Long taskId) {
        if(!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("ProjectId " + projectId + " not found");
        }

        return taskRepository.findById(taskId).map(task -> {
             taskRepository.delete(task);
             return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("TaskId " + taskId + " not found"));
    }
}

