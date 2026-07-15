package com.govind.Ecommerce_DAL.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.govind.Ecommerce_DAL.Exception.ResourceNotFound;
import com.govind.Ecommerce_DAL.dto.OrderUpdateRequest;
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
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User not found"));

		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(new Date());

		List<OrderItem> items = new ArrayList<>();

		for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {

			Product product = productRepo.findById(entry.getKey())
					.orElseThrow(() -> new ResourceNotFound("User not found"));

			OrderItem item = new OrderItem();

			item.setOrder(order);
			item.setProduct(product);
			item.setQuantity(entry.getValue());
			item.setPrice(product.getPrice());

			items.add(item);
		}

		order.getOrderItems().stream().filter(item -> item.getProduct() != null).toList();

		return orderRepo.save(order).getId();

	}

	// READ
	public Page<Order> readAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		return orderRepo.findAll(pageable);
	}

	// DELETE
	@CacheEvict(value = "order", key = "#id")
	public void deleteOrder(Long id) {
		Order o = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found."));
		orderRepo.delete(o);
	}

	// UPDATE
	@CachePut(value = "order", key = "#id")
	public Order updateOrderDetails(Long id, OrderUpdateRequest dto) {
		Order order = orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Order not found."));

		order.getUser().setId(dto.useriId());

		return orderRepo.save(order);
	}

	// SORT
	public Page<Order> sort(int page, int size, String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return orderRepo.findAll(pageable);
	}

	// Find By Id
	@Cacheable(value = "order", key = "#id")
	public Order findById(Long id) {
		return orderRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}

}
