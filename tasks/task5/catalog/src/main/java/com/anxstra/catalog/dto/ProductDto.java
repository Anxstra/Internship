package com.anxstra.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductDto(@NotBlank String name,
                         @NotNull @Positive BigDecimal price,
                         @NotNull @PositiveOrZero Integer quantity,
                         @NotNull @PositiveOrZero Integer deliveryPeriod,
                         @NotBlank String imageUrl,
                         @NotNull Long categoryId,
                         @NotNull Long descriptionId,
                         Long version) {

}
