package com.anxstra.mappers;

import com.anxstra.dto.RoleDto;
import com.anxstra.dto.UserCacheDto;
import com.anxstra.entities.Role;
import com.anxstra.entities.User;
import com.anxstra.enums.StatusType;

import java.util.Set;
import java.util.stream.Collectors;

public class UserBuilder {

    private UserBuilder() {
    }

    public static User createUser(String mail, String password, Set<Role> roles, StatusType status) {

        return User.builder()
                   .email(mail)
                   .password(password)
                   .roles(roles)
                   .status(status)
                   .build();
    }

    public static UserCacheDto createUserCacheDto(User user) {

        Set<RoleDto> roles = user.getRoles().stream()
                                 .map(role -> new RoleDto(role.getAuthority()))
                                 .collect(Collectors.toSet());

        return new UserCacheDto(user.getId(), roles, user.getStatus());
    }
}
