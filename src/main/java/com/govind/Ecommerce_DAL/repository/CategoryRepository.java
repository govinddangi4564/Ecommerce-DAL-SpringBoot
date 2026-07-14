package com.govind.Ecommerce_DAL.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.govind.Ecommerce_DAL.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findAll(Pageable pageable);

	List<Category> findByNameContainingIgnoreCase(String name);
}
