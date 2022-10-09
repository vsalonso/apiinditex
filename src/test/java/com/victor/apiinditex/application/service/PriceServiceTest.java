package com.victor.apiinditex.application.service;

import com.victor.apiinditex.datatest.PriceDataTest;
import com.victor.apiinditex.infrastruture.api.exception.PriceException;
import com.victor.apiinditex.infrastruture.api.service.PriceService;
import com.victor.apiinditex.infrastruture.api.service.PriceServiceAPI;
import com.victor.apiinditex.model.Price;
import com.victor.apiinditex.model.PriceRQ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.victor.apiinditex.datatest.PriceDataTest.buildMockPrices;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceServiceAPI priceFinderRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void shouldReturnPrice() {
        final Integer productId = PriceDataTest.productIdRandom();
        final Integer brandId = PriceDataTest.brandIdRandom();
        final LocalDateTime applyDate = PriceDataTest.applyDateRandom();
        final int priceList = 1;
        final List<Price> prices = buildMockPrices(productId, brandId, priceList);

        given(this.priceFinderRepository.findPriceByProductIdAndBrandIdAndApplyDate(productId, brandId, applyDate))
                .willReturn(prices);

        final Price price = this.priceService.getPrice(PriceRQ.builder()
                .applyDate(applyDate)
                .brandId(brandId)
                .productId(productId)
                .build());
        assertEquals(prices.get(1).price(), price.price());
        assertEquals(prices.get(1).priceList(), priceList);
    }

    @Test
    void shouldNotFoundPrice() {
        final Integer productId = PriceDataTest.productIdRandom();
        final Integer brandId = PriceDataTest.brandIdRandom();
        final LocalDateTime applyDate = PriceDataTest.applyDateRandom();

        given(this.priceFinderRepository.findPriceByProductIdAndBrandIdAndApplyDate(productId, brandId, applyDate))
                .willThrow(new PriceException("Not data found"));

        final PriceRQ priceRequest = PriceRQ.builder()
                .applyDate(applyDate)
                .brandId(brandId)
                .productId(productId)
                .build();
        assertThrows(PriceException.class, () -> this.priceService.getPrice(priceRequest));
    }
}
