package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.FlexUser;

@Repository
public interface UserRepository extends JpaRepository<FlexUser, Long> {

	Optional<FlexUser> findByUsername(String username);

}
