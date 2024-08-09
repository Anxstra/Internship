package com.anxstra.repositories;

import com.anxstra.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph("User.BRIEF")
    Optional<User> findBriefByEmail(String email);

    @EntityGraph("User.BRIEF")
    Optional<User> findBriefById(Long id);

    boolean existsByEmail(String email);
}