package com.anxstra.mappers;

import com.anxstra.entities.Token;
import com.anxstra.entities.User;

public class TokenBuilder {

    private TokenBuilder() {
    }

    public static Token buildToken(String accessToken, String refreshToken, User user) {

        return Token.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .user(user)
                    .build();
    }
}
