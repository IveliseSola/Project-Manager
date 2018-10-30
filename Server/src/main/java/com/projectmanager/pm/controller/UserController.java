package com.projectmanager.pm.controller;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.pm.repository.DeveloperRepository;
import com.projectmanager.pm.repository.UserRepository;
import com.projectmanager.pm.exception.ResourceNotFoundException;
import com.projectmanager.pm.model.User;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	DeveloperRepository  developerRepository;
	
	@Autowired
	EntityManager em;
	
	 private UserRepository userRepository;
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	    
	 public UserController(UserRepository userRepository,
             BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	 }
	 
	@PostMapping("/sign-up")
    public void signUp(@RequestBody User user) { 
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }  
	
	
	@PutMapping("/edit/{username}")
	public User Edit(@RequestBody User user, @PathVariable (value = "username") String username) {
		
		User someUser = repository.findByUsername(username);
		someUser.setPassword(bCryptPasswordEncoder.encode(someUser.getPassword()));
		return repository.save(someUser);
		
	}
    
	@DeleteMapping("/{Id}")
	@Transactional
    public ResponseEntity<?> deleteUser(@PathVariable Long Id) {
        return repository.findById(Id).map(user -> {
      	    Query query = em.createNativeQuery("DELETE FROM project_developers WHERE developer_id = ?");
      	    query.setParameter(1,Id);
      	    query.executeUpdate();
            repository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User Id: " + Id + " not found"));
    }
}
