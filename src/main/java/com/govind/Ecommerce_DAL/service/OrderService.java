package com.govind.Ecommerce_DAL.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.govind.Ecommerce_DAL.entity.Order;
import com.govind.Ecommerce_DAL.entity.OrderItem;
import com.govind.Ecommerce_DAL.entity.Product;
import com.govind.Ecommerce_DAL.entity.User;
import com.govind.Ecommerce_DAL.repository.OrderRepository;
import com.govind.Ecommerce_DAL.repository.ProductRepository;
import com.govind.Ecommerce_DAL.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

	private OrderRepository orderRepo;
	private UserRepository userRepo;
	private ProductRepository productRepo;

	// INSERT

	@Transactional
	public Long createOrder(Long userId, Map<Long, Integer> productQuantities) {
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not Found"));

		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(new Date());

		List<OrderItem> items = new ArrayList<>();

		for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {

			Product product = productRepo.findById(entry.getKey())
					.orElseThrow(() -> new RuntimeException("Product not found"));

			OrderItem item = new OrderItem();

			item.setOrder(order);
			item.setProduct(product);
			item.setQuantity(entry.getValue());
			item.setPrice(product.getPrice());

			items.add(item);
		}

		order.setOrderItems(items);

		return orderRepo.save(order).getId();

	}

	// READ
	public Page<Order> readAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		return orderRepo.findAll(pageable);
	}

}
