package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.NuTaBook;

@Repository
public interface NuTaRepository extends JpaRepository<NuTaBook, Long>,
JpaSpecificationExecutor<NuTaBook>{
	
	
	
}
