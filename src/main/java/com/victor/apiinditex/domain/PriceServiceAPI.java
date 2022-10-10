package com.victor.apiinditex.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceServiceAPI {
    List<Price> findPriceByProductIdAndBrandIdAndApplyDate(Integer productId, Integer brandId, LocalDateTime applyDate);
}
