package com.anxstra.dto;

import com.anxstra.enums.StatusType;

import java.util.Set;

public record UserCacheDto(Long id, Set<RoleDto> roles, StatusType status) {

}
