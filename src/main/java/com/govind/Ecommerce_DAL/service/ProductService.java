package com.govind.Ecommerce_DAL.service;

import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.entity.Category;
import com.govind.Ecommerce_DAL.entity.Product;
import com.govind.Ecommerce_DAL.repository.CategoryRepository;
import com.govind.Ecommerce_DAL.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

	private ProductRepository proRepo;

	private CategoryRepository catRepo;

	public Product addProduct(Long categoryId, Product pro) {
		Category cat = catRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found."));
		cat.addProduct(pro);

		return proRepo.save(pro);
	}
}
