package com.govind.Ecommerce_DAL.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.entity.Category;
import com.govind.Ecommerce_DAL.entity.Product;
import com.govind.Ecommerce_DAL.repository.CategoryRepository;
import com.govind.Ecommerce_DAL.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

	private ProductRepository productRepo;

	private CategoryRepository categoryRepo;

	// INSERT
	public Product addProduct(Long categoryId, Product product) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found."));

		product.setCategory(category);

		return productRepo.save(product);
	}

	// READ
	public Page<Product> readAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepo.findAll(pageable);
	}
}
