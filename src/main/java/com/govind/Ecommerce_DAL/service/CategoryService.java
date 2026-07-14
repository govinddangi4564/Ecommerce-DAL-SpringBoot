package com.govind.Ecommerce_DAL.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.Exception.ResourceNotFound;
import com.govind.Ecommerce_DAL.dto.CategoryUpdateRequest;
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

	// UPDATE
	public Category update(Long id, CategoryUpdateRequest dto) {
		Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Category Not found"));

		category.setName(dto.name());

		return categoryRepo.save(category);
	}

	// SORT
	public Page<Category> sort(int page, int size, String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return categoryRepo.findAll(pageable);
	}

	// Find By Id
	@Cacheable(value = "category", key = "#id")
	public Category findById(Long id) {
		return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}

	// Search By name
	public List<Category> search(String name) {
		return categoryRepo.findByNameContainingIgnoreCase(name);
	}
}
