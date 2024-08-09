package com.anxstra.repositories;

import com.anxstra.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("from Token t where t.user.id = :id and t.isRevoked = false")
    List<Token> findAllByUserIdAndIsRevokedFalse(Long id);

    Optional<Token> findByRefreshToken(String token);
}