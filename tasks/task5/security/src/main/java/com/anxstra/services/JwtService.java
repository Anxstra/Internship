package com.anxstra.services;

import com.anxstra.entities.Token;
import com.anxstra.jwtfilterspringbootstarter.BaseJwtService;
import com.anxstra.jwtfilterspringbootstarter.config.properties.JwtConfigurationProperties;
import com.anxstra.jwtfilterspringbootstarter.exception.ApplicationException;
import com.anxstra.mappers.JwtBuilder;
import com.anxstra.repositories.TokenRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.anxstra.constants.CommonConstants.ACCESS_TOKEN_TTL;
import static com.anxstra.constants.CommonConstants.REFRESH_TOKEN_TTL;
import static com.anxstra.constants.MessageConstants.REFRESH_TOKEN_INVALID_MSG;
import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

@Service
public class JwtService extends BaseJwtService {

    private final TokenRepository tokenRepository;

    public JwtService(JwtConfigurationProperties jwtConfigurationProperties, TokenRepository tokenRepository) {
        super(jwtConfigurationProperties);
        this.tokenRepository = tokenRepository;
    }

    public String generateAccessToken(UserDetails user, String ip) {
        return JwtBuilder.createToken(user, ACCESS_TOKEN_TTL, ip, getSecretKey());
    }

    public String generateRefreshToken(UserDetails user, String ip) {
        return JwtBuilder.createToken(user, REFRESH_TOKEN_TTL, ip, getSecretKey());
    }

    public void isRefreshTokenValid(String token) {

        boolean isRevoked = tokenRepository.findByRefreshToken(token)
                                           .map(Token::getIsRevoked)
                                           .orElse(true);

        if (isRevoked) {
            throw new ApplicationException(badRequestOf(REFRESH_TOKEN_INVALID_MSG));
        }
    }
}
