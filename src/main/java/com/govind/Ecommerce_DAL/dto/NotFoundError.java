package com.govind.Ecommerce_DAL.dto;

import java.time.LocalDateTime;

public record NotFoundError(LocalDateTime timestamp, Integer status, String message) {


}
