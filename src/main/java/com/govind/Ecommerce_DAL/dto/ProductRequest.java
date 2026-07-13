package com.govind.Ecommerce_DAL.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price, Integer stockQuantity,
		Long categoryId) {
}
