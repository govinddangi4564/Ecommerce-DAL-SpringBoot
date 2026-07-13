package com.govind.Ecommerce_DAL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.govind.Ecommerce_DAL.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
