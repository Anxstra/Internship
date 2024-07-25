package com.anxstra.jwtfilterspringbootstarter;

import com.anxstra.jwtfilterspringbootstarter.config.properties.JwtConfigurationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseJwtService {

    private static final String ROLE_CLEANER_REGEX = "^\\[|]$";

    private final JwtConfigurationProperties jwtConfigurationProperties;

    public BaseJwtService(JwtConfigurationProperties jwtConfigurationProperties) {
        this.jwtConfigurationProperties = jwtConfigurationProperties;
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
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

    protected SecretKey getSecretKey() {

        byte[] secretKey = Decoders.BASE64.decode(jwtConfigurationProperties.secret());
        return Keys.hmacShaKeyFor(secretKey);
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
}
