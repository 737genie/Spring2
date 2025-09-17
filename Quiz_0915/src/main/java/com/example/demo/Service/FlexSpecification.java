package com.example.demo.Service;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.Entity.Flex;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class FlexSpecification {
	public static Specification<Flex> search(String keyword) {
		return new Specification<Flex>() {

			@Override
			public Predicate toPredicate(Root<Flex> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				return cb.or(
						cb.like(root.get("title"), "%" + keyword + "%"),
						cb.like(root.get("content"), "%" + keyword + "%")
						);
			}
			
		};
	}
}
