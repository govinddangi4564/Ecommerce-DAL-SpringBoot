package com.govind.Ecommerce_DAL.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDTO {
	private Long id;
	private String name;
	private List<ProductDTO> products;
}
