package com.govind.Ecommerce_DAL.service;

import java.util.Date;
import java.util.Map;

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

	private OrderRepository odrRepo;
	private UserRepository usrRepo;
	private ProductRepository proRepo;

	@Transactional
	public Long createOrder(Long customerId, Map<Long, Integer> productQuantities) {
		User customer = usrRepo.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not Found"));

		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderDate(new Date());
		for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
			Product product = proRepo.findById(entry.getKey())
					.orElseThrow(() -> new RuntimeException("Product not found"));
			OrderItem item = new OrderItem();
			item.setProduct(product);
			item.setQuantity(entry.getValue());
			item.setPrice(product.getPrice());
			order.addOrderItem(item);
		}

		Order savedOrder = odrRepo.save(order);
		return savedOrder.getId();
	}

}
