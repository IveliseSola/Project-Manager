package com.projectmanager.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.projectmanager.pm.model.*;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long>{
	
	@Query( value = "SELECT * FROM developers d INNER JOIN developer_languages dl on d.user_id = dl.developer_user_id  WHERE dl.languages= ?1", nativeQuery = true)
	List<Developer> findDevelopersByLanguages(String language);
	
	@Query( value = "SELECT * FROM developers d where d.user_id = ?1", nativeQuery = true)
	Developer findThatGuy(Long userid);
	
}
