package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.DreamProject;

@Repository
public interface DreamProjectRepository extends JpaRepository<DreamProject, Long>{
	
	Boolean existsByActivationCode(String activationCode);


	
}
