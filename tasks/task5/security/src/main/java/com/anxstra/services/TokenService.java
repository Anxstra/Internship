package com.anxstra.services;

import com.anxstra.entities.Token;
import com.anxstra.entities.User;
import com.anxstra.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    @Transactional
    public void revokePrevious(User user) {
        tokenRepository.findAllByUserIdAndIsRevokedFalse(user.getId())
                       .forEach(token -> token.setIsRevoked(true));
    }

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }
}
