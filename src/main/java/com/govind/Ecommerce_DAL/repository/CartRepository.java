package com.govind.Ecommerce_DAL.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.govind.Ecommerce_DAL.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Page<Cart> findAll(Pageable pageable);
}
