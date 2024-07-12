package com.anxstra.mappers;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.anxstra.jwtfilterspringbootstarter.JwtAuthFilter.IP_CLAIM;
import static com.anxstra.jwtfilterspringbootstarter.JwtAuthFilter.ROLES_CLAIM;


public class JwtBuilder {

    private JwtBuilder() {
    }

    public static String createToken(UserDetails user, long ttl, String ip, SecretKey secret) {

        return Jwts.builder()
                   .header()
                   .type("JWT")
                   .and()
                   .subject(user.getUsername())
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + ttl))
                   .claim(IP_CLAIM, ip)
                   .claim(ROLES_CLAIM, user.getAuthorities().toString())
                   .signWith(secret, Jwts.SIG.HS512)
                   .compact();
    }
}
