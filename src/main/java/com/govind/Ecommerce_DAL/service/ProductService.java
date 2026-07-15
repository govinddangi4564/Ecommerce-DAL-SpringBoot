package com.govind.Ecommerce_DAL.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.Exception.ResourceNotFound;
import com.govind.Ecommerce_DAL.dto.ProductUpdateRequest;
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
				.orElseThrow(() -> new ResourceNotFound("Category not found"));

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

	// UPDATE
	@CachePut(value = "product", key = "#id")
	public Product updateProduct(Long id, ProductUpdateRequest dto) {
		Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Product not found."));

		product.setName(dto.name());
		product.setDescription(dto.description());
		product.setPrice(dto.price());
		product.setStockQuantity(dto.stockQuantity());

		return productRepo.save(product);
	}

	// SORT
	public Page<Product> sort(int page, int size, String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return productRepo.findAll(pageable);
	}

	// Find By Id
	@Cacheable(value = "product", key = "#id")
	public Product findById(Long id) {
		return productRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}

	// Search by name
	public List<Product> search(String name) {
		return productRepo.findByNameContainingIgnoreCase(name);
	}
}
