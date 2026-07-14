package com.govind.Ecommerce_DAL.controller;

import java.util.List;

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

import com.govind.Ecommerce_DAL.dto.CategoryUpdateRequest;
import com.govind.Ecommerce_DAL.entity.Category;
import com.govind.Ecommerce_DAL.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
	}

	@GetMapping
	public Page<Category> readAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return categoryService.readAll(page, size);
	}

	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return "Delete Successful";
	}

	@PutMapping("/{id}")
	public Category update(@PathVariable Long id, @RequestBody CategoryUpdateRequest dto) {
		return categoryService.update(id, dto);
	}

	@GetMapping("/sort")
	public Page<Category> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String field,
			@RequestParam(defaultValue = "asc") String direction) {

		return categoryService.sort(page, size, field, direction);
	}

	@GetMapping("/{id}")
	public Category findByid(@PathVariable Long id) {
		return categoryService.findById(id);
	}

	@GetMapping("/search")
	public List<Category> search(@RequestParam String name) {
		return categoryService.search(name);
	}
}
