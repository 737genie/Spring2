package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Recipe;

@Repository
public interface LabRepository extends JpaRepository<Recipe, Long>{
	@Query("SELECT r FROM Recipe r JOIN FETCH r.user")
	List<Recipe> findAllWithUsers();
}
