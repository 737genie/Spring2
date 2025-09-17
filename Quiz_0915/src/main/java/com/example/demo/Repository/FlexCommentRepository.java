package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.CommentDto;
import com.example.demo.Entity.Flex;
import com.example.demo.Entity.FlexComment;

@Repository
public interface FlexCommentRepository extends JpaRepository<FlexComment, Long> {

	List<FlexComment> findByFlex(Flex flex);
	
}
