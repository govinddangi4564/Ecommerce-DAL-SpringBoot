package com.govind.Ecommerce_DAL.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Ecommerce_DAL.dto.OrderRequestDTO;
import com.govind.Ecommerce_DAL.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Long> createOrder(@RequestBody OrderRequestDTO orderRequest) {
		Long orderId = orderService.createOrder(orderRequest.getCustomerId(), orderRequest.getProductQuantities());
		return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
	}
}
