package com.anxstra.services;

import com.anxstra.entities.User;
import com.anxstra.jwtfilterspringbootstarter.exception.ApplicationException;
import com.anxstra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.anxstra.constants.MessageConstants.USER_NOT_FOUND_MSG;
import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findBriefByEmail(username)
                             .orElseThrow(() -> new ApplicationException(badRequestOf(USER_NOT_FOUND_MSG)));
    }

    public User loadUserById(Long id) {

        return userRepository.findBriefById(id)
                             .orElseThrow(() -> new ApplicationException(badRequestOf(USER_NOT_FOUND_MSG)));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
