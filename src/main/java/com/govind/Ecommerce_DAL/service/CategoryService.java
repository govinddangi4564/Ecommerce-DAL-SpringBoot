package com.govind.Ecommerce_DAL.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.Exception.ResourceNotFound;
import com.govind.Ecommerce_DAL.entity.Category;
import com.govind.Ecommerce_DAL.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

	private CategoryRepository categoryRepo;

	// INSERT
	public Category createCategory(Category cat) {
		return categoryRepo.save(cat);
	}

	// READ
	public Page<Category> readAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return categoryRepo.findAll(pageable);
	}

	// DELETE
	@CacheEvict(value = "category", key = "#id")
	public void deleteCategory(Long id) {
		Category c = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Category Not found"));
		categoryRepo.delete(c);
	}

	// Find By Id
	@Cacheable(value = "category", key = "#id")
	public Category findById(Long id) {
		return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}
}
