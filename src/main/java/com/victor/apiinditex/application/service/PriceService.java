package com.victor.apiinditex.application.service;

import com.victor.apiinditex.application.exception.PriceNotFoundException;
import com.victor.apiinditex.application.request.PriceRQ;
import com.victor.apiinditex.domain.Price;
import com.victor.apiinditex.domain.PriceServiceAPI;
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
