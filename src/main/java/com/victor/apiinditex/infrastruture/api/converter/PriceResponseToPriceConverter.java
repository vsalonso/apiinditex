package com.victor.apiinditex.infrastruture.api.converter;


import com.victor.apiinditex.model.Price;
import com.victor.apiinditex.price.infrastructure.generate.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public interface PriceResponseToPriceConverter {

    @Mapping(target = "productId", source = "productId")
    PriceResponse priceToPriceResponse(Price obj);
}
