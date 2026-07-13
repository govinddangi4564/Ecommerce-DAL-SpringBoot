package com.govind.Ecommerce_DAL.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Ecommerce_DAL.dto.ProductDTO;
import com.govind.Ecommerce_DAL.entity.Product;
import com.govind.Ecommerce_DAL.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setStockQuantity(productDto.getStockQuantity());
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDto.getCategoryId(), product));
	}
}
