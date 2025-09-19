package com.example.demo.Domain;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class BookSpecification {

	public static Specification<NuTaBook> search(String keyword) {
		return new Specification<NuTaBook>() {

			@Override
			public Predicate toPredicate(Root<NuTaBook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				return cb.or(
						cb.like(root.get("title"), "%" + keyword + "%"),
						cb.like(root.get("author"), "%" + keyword + "%")
						);
			}
			
		};
	}

}
