package com.victor.apiinditex.infrastruture.bbdd.repository;

import com.victor.apiinditex.infrastruture.api.service.PriceServiceAPI;
import com.victor.apiinditex.infrastruture.bbdd.converter.PriceEntityToPriceConverter;
import com.victor.apiinditex.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceRepository implements PriceServiceAPI {

    private final SpringDataPriceRepository springDataPriceRepository;
    private final PriceEntityToPriceConverter priceEntityToPriceConverter;

    @Override
    public List<Price> findPriceByProductIdAndBrandIdAndApplyDate(final Integer productId,
                                                                  final Integer brandId,
                                                                  final LocalDateTime applyDate) {

        return this.priceEntityToPriceConverter.priceEntityToPrices(this.springDataPriceRepository
                .findPriceByProductIdAndBrandIdAndApplyDate(productId, brandId, Timestamp.valueOf(applyDate)));
    }
}
