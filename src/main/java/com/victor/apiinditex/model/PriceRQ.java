package com.victor.apiinditex.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PriceRQ(Integer productId, Integer brandId, LocalDateTime applyDate) {
}
