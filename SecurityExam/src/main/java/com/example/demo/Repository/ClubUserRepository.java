package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.ClubUser;
import com.example.demo.Domain.ClubUser.ClubRole;

@Repository
public interface ClubUserRepository extends JpaRepository<ClubUser, Long>{
	 Optional<ClubUser> findByUsername(String username);
	    
    Optional<ClubUser> findByEmail(String email);
    
    boolean existByUsername(String username);
    
    boolean existByEmail(String email);

	long countByEnabledTrue();
     
    long countByRolesContaining(ClubRole role);

}
