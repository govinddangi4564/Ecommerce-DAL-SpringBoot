package com.govind.Ecommerce_DAL.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Ecommerce_DAL.dto.UserUpdateRequest;
import com.govind.Ecommerce_DAL.entity.User;
import com.govind.Ecommerce_DAL.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User us) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(us));
	}

	@GetMapping
	public Page<User> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		return userService.readAll(page, size);
	}

	@DeleteMapping("/{id}")
	public String removeUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "Delete Successfully";
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest dto) {
		return userService.updateUser(id, dto);
	}

	@GetMapping("/sort")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String field, @RequestParam(defaultValue = "asc") String direction) {

		return userService.sort(page, size, field, direction);
	}

	@GetMapping("/{id}")
	public User findByid(@PathVariable Long id) {
		return userService.findById(id);
	}

	@GetMapping("/search")
	public List<User> search(@RequestParam String query) {
		return userService.search(query);
	}

	@GetMapping("/search/username")
	public User findByUsername(@RequestParam String username) {
		return userService.findByEmail(username);
	}

	@GetMapping("/search/email")
	public User findByEmail(@RequestParam String email) {
		return userService.findByEmail(email);
	}
}
