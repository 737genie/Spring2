package com.example.demo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.DreamFragment;

@Repository
public interface DreamFragmentRepository extends JpaRepository<DreamFragment, Long>{
	@Query("SELECT d FROM DreamFragment d WHERE d.dreamProject IS NULL")
	List<DreamFragment> findByDreamProjectIsNull();

	
}

