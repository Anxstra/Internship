package com.anxstra.catalog.dto;

import java.math.BigDecimal;

public record ProductListDto(Long Id,
                             String name,
                             BigDecimal price,
                             Integer quantity,
                             Integer deliveryPeriod,
                             String imageUrl,
                             Long descriptionId) {

}
