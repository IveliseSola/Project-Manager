package com.projectmanager.pm.repository;

import com.projectmanager.pm.model.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	@Query(value = "SELECT * FROM developers d INNER JOIN project_developers pd ON d.user_id = pd.developer_id WHERE pd.project_id = ?1", nativeQuery = true)
	List<Object[]> developersByProject(Long projectId);
		
}
