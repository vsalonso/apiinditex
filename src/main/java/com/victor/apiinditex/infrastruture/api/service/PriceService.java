package com.victor.apiinditex.infrastruture.api.service;

import com.victor.apiinditex.infrastruture.api.exception.PriceNotFoundException;
import com.victor.apiinditex.model.Price;
import com.victor.apiinditex.model.PriceRQ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceServiceAPI priceServiceAPI;

    public Price getPrice(final PriceRQ priceRequest) {
        final List<Price> prices = this.priceServiceAPI.findPriceByProductIdAndBrandIdAndApplyDate(
                priceRequest.productId(),
                priceRequest.brandId(),
                priceRequest.applyDate());

        return prices.stream()
                .max(Comparator.comparingInt(Price::priceList))
                .orElseThrow(() -> new PriceNotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));

    }
}
