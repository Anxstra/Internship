package com.anxstra.dto;

import com.anxstra.enums.StatusType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserRegisterDto(@NotBlank String email,
                              @NotBlank String password,
                              @NotNull Set<@Valid RoleDto> roles,
                              @NotNull StatusType status) {

}