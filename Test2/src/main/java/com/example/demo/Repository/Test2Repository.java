package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.TestPost;

@Repository
public interface Test2Repository extends JpaRepository<TestPost, Long> {


}
