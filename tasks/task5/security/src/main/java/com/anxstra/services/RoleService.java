package com.anxstra.services;

import com.anxstra.dto.RoleDto;
import com.anxstra.entities.Role;
import com.anxstra.jwtfilterspringbootstarter.exception.ApplicationException;
import com.anxstra.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.anxstra.constants.MessageConstants.ROLE_NOT_FOUND_MSG;
import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Set<Role> findAll(Set<RoleDto> roles) {

        Set<String> rolesNames = roles.stream()
                                      .map(RoleDto::name)
                                      .collect(Collectors.toSet());

        Set<Role> result = roleRepository.findRolesByNameIn(rolesNames);

        if (result.size() != roles.size()) {
            throw new ApplicationException(badRequestOf(ROLE_NOT_FOUND_MSG));
        }

        return result;
    }
}
