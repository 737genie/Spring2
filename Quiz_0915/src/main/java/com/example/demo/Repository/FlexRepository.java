package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Flex;

@Repository
public interface FlexRepository extends JpaRepository<Flex, Long> {

}
