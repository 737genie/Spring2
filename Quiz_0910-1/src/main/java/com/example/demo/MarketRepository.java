package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long>{
	
	@Query(value="SELECT m FROM Market m WHERE LOWER(m.title) LIKE CONCAT('%', LOWER(:keyword), '%') or LOWER(m.description) LIKE CONCAT('%', LOWER(:keyword), '%')")
	List<Market> findKeyword(@Param("keyword") String keyword);
	
}
