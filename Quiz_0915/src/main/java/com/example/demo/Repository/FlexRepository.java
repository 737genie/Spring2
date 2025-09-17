package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Flex;
import com.example.demo.Entity.FlexUser;

@Repository
public interface FlexRepository extends JpaRepository<Flex, Long>
, JpaSpecificationExecutor<Flex> {


}
