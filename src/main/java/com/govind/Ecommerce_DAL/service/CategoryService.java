package com.govind.Ecommerce_DAL.service;

import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.entity.Category;
import com.govind.Ecommerce_DAL.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

	private CategoryRepository catRepo;

	public Category createCategory(Category cat) {
		return catRepo.save(cat);
	}

}
