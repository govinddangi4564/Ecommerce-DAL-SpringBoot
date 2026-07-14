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

import com.govind.Ecommerce_DAL.dto.ProductRequest;
import com.govind.Ecommerce_DAL.entity.Product;
import com.govind.Ecommerce_DAL.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody ProductRequest dto) {
		Product product = new Product();

		product.setName(dto.name());
		product.setDescription(dto.description());
		product.setPrice(dto.price());
		product.setStockQuantity(dto.stockQuantity());

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(dto.categoryId(), product));
	}

	@GetMapping
	public Page<Product> readAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		return productService.readAll(page, size);
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "Product successfully deleted.";
	}

	@GetMapping("/{id}")
	public Product findByid(@PathVariable Long id) {
		return productService.findById(id);
	}

}
