package com.victor.apiinditex.infrastruture.bbdd.converter;

import com.victor.apiinditex.infrastruture.bbdd.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN)
public interface PriceEntityToPriceConverter {

    @Mapping(target = "productId", source = "productId")
    com.victor.apiinditex.domain.Price priceEntityToPrice(PriceEntity entity);

    List<com.victor.apiinditex.domain.Price> priceEntityToPrices(List<PriceEntity> entity);
}
