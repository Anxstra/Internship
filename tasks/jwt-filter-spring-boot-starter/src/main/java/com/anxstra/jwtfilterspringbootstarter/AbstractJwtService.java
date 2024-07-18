package com.anxstra.jwtfilterspringbootstarter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public abstract class AbstractJwtService {

    private static final String ROLE_CLEANER_REGEX = "^\\[|]$";

    @Value("${jwt.secret}")
    protected String secret;

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractIp(String token, String ipClaim) {
        return extractClaim(token, claims -> claims.get(ipClaim, String.class));
    }

    public Set<GrantedAuthority> extractRoles(String token, String roleClaim) {

        String rolesClaim = extractClaim(token, claims -> claims.get(roleClaim, String.class));
        Set<GrantedAuthority> roles = Stream.of(rolesClaim.replaceAll(ROLE_CLEANER_REGEX, "")
                                                          .split(","))
                                            .map(SimpleGrantedAuthority::new)
                                            .collect(Collectors.toSet());

        return roles;
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    protected Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    protected SecretKey getSecretKey() {

        byte[] secretKey = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretKey);
    }

    protected <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {

        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    protected Claims extractAllClaims(String token) {

        return Jwts.parser()
                   .verifyWith(getSecretKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }
}
