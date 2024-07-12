package com.anxstra.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DescriptionDto(@NotBlank String text,
                             @NotNull @Positive Integer weight,
                             @NotNull @Positive Integer length,
                             @NotNull @Positive Integer width,
                             @NotNull @Positive Integer depth,
                             Long version) {

}
