package com.govind.Ecommerce_DAL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.govind.Ecommerce_DAL.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
