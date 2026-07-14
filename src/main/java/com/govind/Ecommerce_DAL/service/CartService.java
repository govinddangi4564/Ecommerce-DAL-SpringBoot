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
import com.govind.Ecommerce_DAL.dto.CartUpdateRequest;
import com.govind.Ecommerce_DAL.entity.Cart;
import com.govind.Ecommerce_DAL.entity.Product;
import com.govind.Ecommerce_DAL.entity.User;
import com.govind.Ecommerce_DAL.repository.CartRepository;
import com.govind.Ecommerce_DAL.repository.ProductRepository;
import com.govind.Ecommerce_DAL.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {

	private CartRepository cartRepo;
	private UserRepository userRepo;
	private ProductRepository productRepo;

	// INSERT
	public Cart createCart(Long userId, List<Long> productIds) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found"));

		List<Product> products = productRepo.findAllById(productIds);

		Cart cart = new Cart();

		cart.setUser(user);
		cart.setProducts(products);

		return cartRepo.save(cart);
	}

	// READ
	public Page<Cart> viewCart(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		return cartRepo.findAll(pageable);
	}

	// DELETE
	@CacheEvict(value = "Cart", key = "#id")
	public void deleteCart(Long id) {
		Cart c = cartRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Cart not found"));
		cartRepo.delete(c);
	}

	// UPDATE
	public Cart update(Long id, CartUpdateRequest dto) {
		Cart cart = cartRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Cart not found"));

		cart.getUser().setId(dto.id());

		return cartRepo.save(cart);
	}

	// SORT
	public Page<Cart> sort(int page, int size, String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return cartRepo.findAll(pageable);
	}

	// Find By Id
	@Cacheable(value = "Cart", key = "#id")
	public Cart findById(Long id) {
		return cartRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}

}
