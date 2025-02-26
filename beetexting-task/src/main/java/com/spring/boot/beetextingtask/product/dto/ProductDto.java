package com.spring.boot.beetextingtask.product.dto;

import java.math.BigDecimal;

public record ProductDto(String name, String category, Double price, Long stackQuantity) {
}
