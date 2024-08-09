package com.anxstra.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDto(@NotBlank String name) {

}