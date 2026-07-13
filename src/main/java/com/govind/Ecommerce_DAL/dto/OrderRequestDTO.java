package com.govind.Ecommerce_DAL.dto;

import java.util.Map;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long customerId;
    private Map<Long, Integer> productQuantities;
}
