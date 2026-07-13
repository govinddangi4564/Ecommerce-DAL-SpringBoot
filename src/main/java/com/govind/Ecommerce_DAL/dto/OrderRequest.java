package com.govind.Ecommerce_DAL.dto;

import java.util.Map;

public record OrderRequest(Long userId, Map<Long, Integer> productQuantities) {
}