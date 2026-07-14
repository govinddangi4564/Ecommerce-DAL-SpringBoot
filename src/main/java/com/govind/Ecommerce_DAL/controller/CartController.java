package com.govind.Ecommerce_DAL.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Ecommerce_DAL.dto.CartRequest;
import com.govind.Ecommerce_DAL.dto.CartUpdateRequest;
import com.govind.Ecommerce_DAL.entity.Cart;
import com.govind.Ecommerce_DAL.service.CartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {

	private CartService cartService;

	@PostMapping
	public ResponseEntity<Cart> createCart(@RequestBody CartRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(dto.userId(), dto.productIds()));
	}

	@GetMapping
	public Page<Cart> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		return cartService.viewCart(page, size);
	}

	@DeleteMapping("/{id}")
	public String deleteCart(@PathVariable Long id) {
		cartService.deleteCart(id);
		return "Delete successful";
	}

	@PutMapping("/{id}")
	public Cart update(@PathVariable Long id, @RequestBody CartUpdateRequest dto) {
		return cartService.update(id, dto);
	}

	@GetMapping("/sort")
	public Page<Cart> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String field, @RequestParam(defaultValue = "asc") String direction) {

		return cartService.sort(page, size, field, direction);
	}

	@GetMapping("/{id}")
	public Cart findByid(@PathVariable Long id) {
		return cartService.findById(id);
	}
}
