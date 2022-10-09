package com.victor.apiinditex.infrastruture.api.service;

import com.victor.apiinditex.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceServiceAPI {
    List<Price> findPriceByProductIdAndBrandIdAndApplyDate(Integer productId, Integer brandId, LocalDateTime applyDate);
}
