package com.victor.apiinditex.infrastruture.api.controller;


import com.victor.apiinditex.application.request.PriceRQ;
import com.victor.apiinditex.application.service.PriceService;
import com.victor.apiinditex.domain.Price;
import com.victor.apiinditex.infrastruture.api.converter.PriceResponseToPriceConverter;
import com.victor.apiinditex.price.infrastructure.generate.api.PricesApi;
import com.victor.apiinditex.price.infrastructure.generate.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.victor.apiinditex.utils.DateUtils.convertStringToLocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PriceFinderController implements PricesApi {

    private final PriceService priceService;
    private final PriceResponseToPriceConverter priceResponseToPriceMapper;

    @Override
    public ResponseEntity<PriceResponse> getPrice(@NotNull @Valid final Integer productId,
                                                  @NotNull @Valid final Integer brandId,
                                                  @NotNull @Valid final String applyDate) {

        final Price price = this.priceService.getPrice(PriceRQ.builder()
                .productId(productId)
                .brandId(brandId)
                .applyDate(convertStringToLocalDate(applyDate))
                .build());

        return ResponseEntity.ok().body(this.priceResponseToPriceMapper.priceToPriceResponse(price));
    }
}
