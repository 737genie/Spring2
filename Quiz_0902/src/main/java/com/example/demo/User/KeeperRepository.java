package com.example.demo.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeeperRepository extends JpaRepository<DiaryKeeper, Long> {

	Optional<DiaryKeeper> findByUsername(String username);

}
