package com.govind.Ecommerce_DAL.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
