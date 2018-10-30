package com.projectmanager.pm.controller;

import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectmanager.pm.repository.*;
import com.projectmanager.pm.model.*;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/developers")
@RestController
public class DeveloperController {
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/")
    public Page<Developer> getAllDevelopers(Pageable pageable) {
        return developerRepository.findAll(pageable);
    }
	
	@GetMapping("/{language}")
	public ArrayList<Developer> getByLanguage(@PathVariable (value = "language") String language){
		return (ArrayList<Developer>) developerRepository.findDevelopersByLanguages(language);
	}
	
	
	@PostMapping("/profile/{username}")
    public Developer addDeveloper( @PathVariable (value = "username") String username, @Valid @RequestBody Developer developer) {
		
		 User someUser = userRepository.findByUsername(username);
		 developer.setId(someUser.getId());
		 developer.setUser(someUser);
		 return developerRepository.save(developer);
    }
	
		
	@PutMapping("/profile/edit/{id}")
	public Optional<Object> editDeveloper( @PathVariable (value = "id") Long id, @Valid @RequestBody Developer developerRequest) {
		     
		return developerRepository.findById(id).map(developer -> {
		    	 
		    	 developer.setAddress(developerRequest.getAddress());
		    	 developer.setEmail(developerRequest.getEmail());
		    	 developer.setFirstName(developerRequest.getFirstName());
	             developer.setLastName(developerRequest.getLastName());
	             developer.setPhone(developerRequest.getPhone());
	             developer.setUser(developerRequest.getUser());
	             developer.setYearExperience(developerRequest.getYearExperience());
	             developer.setLanguages(developerRequest.getLanguages());
	             return developerRepository.save(developer);
		     });
           
	}
	
	

}
