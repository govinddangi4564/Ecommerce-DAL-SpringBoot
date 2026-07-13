package com.govind.Ecommerce_DAL.dto;

import java.util.List;

public record CartRequest(Long userId, List<Long> productIds) {

}
