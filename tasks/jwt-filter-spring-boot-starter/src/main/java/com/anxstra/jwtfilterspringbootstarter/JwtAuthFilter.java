package com.anxstra.jwtfilterspringbootstarter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String IP_CLAIM = "ip";

    public static final String ROLES_CLAIM = "roles";

    private static final String BEARER_PREFIX = "Bearer ";

    private final AbstractJwtService abstractJwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(header) || !header.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = header.substring(BEARER_PREFIX.length());
        String username = abstractJwtService.extractSubject(jwt);
        String ip = abstractJwtService.extractIp(jwt, IP_CLAIM);
        Set<GrantedAuthority> roles = abstractJwtService.extractRoles(jwt, ROLES_CLAIM);

        if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            if (!abstractJwtService.isTokenExpired(jwt) && ip.equals(request.getRemoteAddr())) {

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, roles);

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                request.logout();
            }
        }

        filterChain.doFilter(request, response);
    }
}
