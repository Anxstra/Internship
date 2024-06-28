package com.anxstra.dao.repositories;

import com.anxstra.entities.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Integer id);

    Optional<User> findByName(String fullName);
}
