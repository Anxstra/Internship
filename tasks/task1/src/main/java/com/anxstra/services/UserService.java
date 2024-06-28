package com.anxstra.services;

import com.anxstra.dao.implementations.UserRepositoryImpl;
import com.anxstra.dao.repositories.UserRepository;
import com.anxstra.entities.ShowFor;
import com.anxstra.entities.User;
import com.anxstra.entities.enums.ShowType;
import com.anxstra.exceptions.UserNotFoundException;
import com.anxstra.gson.config.AppConfigurer;

import java.util.List;

public class UserService {

    private static final String ID_NOT_FOUND_MSG = "User with id %d not found";

    private static final String NAME_NOT_FOUND_MSG = "User with name %s not found";

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepositoryImpl();
    }

    private List<User> findAllByIds(List<Integer> ids) {
        return ids.stream()
                  .map(id -> userRepository.findById(id)
                                           .orElseThrow(() -> new UserNotFoundException(String.format(ID_NOT_FOUND_MSG, id))))
                  .toList();
    }

    private List<User> findAllByNames(List<String> fullNames) {
        return fullNames.stream()
                        .map(name -> userRepository.findByName(name)
                                                   .orElseThrow(() -> new UserNotFoundException(
                                                           String.format(NAME_NOT_FOUND_MSG, name))))
                        .toList();
    }

    public List<User> findAllByShowType() {
        ShowFor showFor = AppConfigurer.getSetting()
                                       .getShowFor();
        List<User> users;
        if (showFor.getType() == ShowType.ID) {
            List<Integer> ids = showFor.getUsers()
                                       .stream()
                                       .map(Integer::valueOf)
                                       .toList();
            users = findAllByIds(ids);
        } else {
            users = findAllByNames(showFor.getUsers());
        }
        return users;
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new UserNotFoundException(String.format(ID_NOT_FOUND_MSG, id)));
    }

}
