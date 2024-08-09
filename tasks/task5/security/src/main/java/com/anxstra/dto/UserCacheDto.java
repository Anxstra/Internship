package com.anxstra.dto;

import com.anxstra.enums.StatusType;

import java.util.Set;

public record UserCacheDto(String email, Set<RoleDto> roles, StatusType status) {

}
