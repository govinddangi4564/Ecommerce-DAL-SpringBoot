package com.govind.Ecommerce_DAL.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

	    User user = userRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

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
}
