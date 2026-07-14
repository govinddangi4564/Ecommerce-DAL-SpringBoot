package com.govind.Ecommerce_DAL.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.govind.Ecommerce_DAL.dto.NotFoundError;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<NotFoundError> notfoundException(ResourceNotFound e) {

		NotFoundError error = new NotFoundError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
