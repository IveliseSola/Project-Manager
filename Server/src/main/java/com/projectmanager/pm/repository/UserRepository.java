package com.projectmanager.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.projectmanager.pm.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
	User findByUsername(String username);

}