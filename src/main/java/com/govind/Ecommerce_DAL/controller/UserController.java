package com.govind.Ecommerce_DAL.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Page<User> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return userService.readAll(page, size);
	}

	@DeleteMapping("/{id}")
	public String removeUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "Delete Successfully";
	}

}
