package com.victor.apiinditex.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Price(Integer productId, Integer brandId, Integer priceList,
                    LocalDateTime startDate, LocalDateTime endDate, Double price) {
}

