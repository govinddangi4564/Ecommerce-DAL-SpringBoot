package com.govind.Ecommerce_DAL.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Ecommerce_DAL.dto.OrderRequest;
import com.govind.Ecommerce_DAL.entity.Order;
import com.govind.Ecommerce_DAL.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Long> createOrder(@RequestBody OrderRequest orderRequest) {
		Long orderId = orderService.createOrder(orderRequest.userId(), orderRequest.productQuantities());

		return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
	}

	@GetMapping
	public Page<Order> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		return orderService.readAll(page, size);
	}

	@DeleteMapping("/{id}")
	public String deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return "Delete successful";
	}

	@GetMapping("/{id}")
	public Order findByid(@PathVariable Long id) {
		return orderService.findById(id);
	}
}
