package com.anxstra.catalog.dto;

import com.anxstra.jooq.db.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDto(@NotBlank String name,
                          @NotNull CategoryType type,
                          Long parentId,
                          Long version) {

}
