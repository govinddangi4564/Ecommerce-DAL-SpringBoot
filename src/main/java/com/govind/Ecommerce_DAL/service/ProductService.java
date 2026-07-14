package com.govind.Ecommerce_DAL.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.Exception.ResourceNotFound;
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
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("User not found"));

		product.setCategory(category);

		return productRepo.save(product);
	}

	// READ
	public Page<Product> readAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepo.findAll(pageable);
	}

	// DELETE
	@CacheEvict(value = "product", key = "#id")
	public void deleteProduct(Long id) {
		Product p = productRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Product not found."));
		productRepo.delete(p);
	}

	// Find By Id
	@Cacheable(value = "product", key = "#id")
	public Product findById(Long id) {
		return productRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}
}
