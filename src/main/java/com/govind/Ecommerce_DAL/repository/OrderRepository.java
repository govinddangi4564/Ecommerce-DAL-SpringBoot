package com.govind.Ecommerce_DAL.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.govind.Ecommerce_DAL.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Page<Order> findAll(Pageable pageable);
}
