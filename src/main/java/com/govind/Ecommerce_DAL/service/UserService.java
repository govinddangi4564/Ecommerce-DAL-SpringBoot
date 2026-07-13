package com.govind.Ecommerce_DAL.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.entity.User;
import com.govind.Ecommerce_DAL.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepo;

	// INSERT
	public User createUser(User user) {
		if (user.getCustomerProfile() != null) {
			user.getCustomerProfile().setUser(user);
		}

		return userRepo.save(user);
	}

	// READ
	public Page<User> readAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return userRepo.findAll(pageable);
	}
}
