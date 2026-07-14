package com.govind.Ecommerce_DAL.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.govind.Ecommerce_DAL.Exception.ResourceNotFound;
import com.govind.Ecommerce_DAL.dto.UserUpdateRequest;
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

	// DELETE
	@CacheEvict(value = "user", key = "#id")
	public void deleteUser(Long id) {
		User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
		userRepo.delete(u);
	}

	// UPDATE
	@CachePut(value = "user", key = "#id")
	public User updateUser(Long id, UserUpdateRequest dto) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User Not Found."));

		user.setUsername(dto.username());
		user.setEmail(dto.email());
		user.getCustomerProfile().setName(dto.name());
		user.getCustomerProfile().setAddress(dto.address());

		return userRepo.save(user);
	}

	// SORT
	public Page<User> sort(int page, int size, String field, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return userRepo.findAll(pageable);
	}

	// Find By Id
	@Cacheable(value = "user", key = "#id")
	public User findById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}

	// Search by Name, Address, Phone
	public List<User> search(String query) {
		return userRepo.searchUsers(query);
	}

	// Find by username
	@Cacheable(value = "user", key = "#username")
	public User findByUsername(String username) {
		return userRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFound("User Not found"));
	}

	// Find by username
	@Cacheable(value = "user", key = "#email")
	public User findByEmail(String email) {
		return userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFound("User Not found"));
	}
}
