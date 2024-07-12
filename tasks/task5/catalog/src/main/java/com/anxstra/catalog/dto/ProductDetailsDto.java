package com.anxstra.catalog.dto;

import java.math.BigDecimal;

public record ProductDetailsDto(
        Long id,
        String name,
        BigDecimal price,
        Integer quantity,
        Integer deliveryPeriod,
        String imageUrl,
        DescriptionDetailsDto description,
        Long version) {

}
