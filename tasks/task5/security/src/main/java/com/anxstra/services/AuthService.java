package com.anxstra.services;

import com.anxstra.cache.UserCacheUtils;
import com.anxstra.dto.AuthResponse;
import com.anxstra.dto.UserLoginDto;
import com.anxstra.dto.UserRegisterDto;
import com.anxstra.entities.Role;
import com.anxstra.entities.Token;
import com.anxstra.entities.User;
import com.anxstra.enums.StatusType;
import com.anxstra.jwtfilterspringbootstarter.exception.ApplicationException;
import com.anxstra.mappers.TokenBuilder;
import com.anxstra.mappers.UserBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.anxstra.constants.CommonConstants.BEARER_PREFIX;
import static com.anxstra.constants.MessageConstants.USER_EXISTS_MSG;
import static com.anxstra.jwtfilterspringbootstarter.JwtAuthFilter.IP_CLAIM;
import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final JwtService jwtService;

    private final RoleService roleService;

    private final TokenService tokenService;

    private final UserCacheUtils userCache;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public void register(UserRegisterDto userDto) {

        if (userService.existsByEmail(userDto.email())) {
            throw new ApplicationException(badRequestOf(USER_EXISTS_MSG));
        }

        Set<Role> roles = roleService.findAll(userDto.roles());
        String password = passwordEncoder.encode(userDto.password());
        User user = UserBuilder.createUser(userDto.email(), password, roles, StatusType.ACTIVE);

        userService.save(user);
    }

    public AuthResponse authenticate(UserLoginDto userDto, HttpServletRequest request) {

        User user = (User) authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(userDto.email(), userDto.password())).getPrincipal();

        userCache.put(user);

        String ip = request.getRemoteAddr();

        String accessToken = jwtService.generateAccessToken(user, ip);
        String refreshToken = jwtService.generateRefreshToken(user, ip);

        return revokePreviousAndSave(user, accessToken, refreshToken);
    }

    private AuthResponse revokePreviousAndSave(User user, String accessToken, String refreshToken) {

        Token token = TokenBuilder.buildToken(accessToken, refreshToken, user);

        tokenService.revokePrevious(user);
        tokenService.saveToken(token);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refresh(HttpServletRequest request) {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwt = header.substring(BEARER_PREFIX.length());
        String ip = jwtService.extractIp(jwt, IP_CLAIM);

        jwtService.isRefreshTokenValid(jwt);

        User user = retrieveUser(jwt);
        userCache.put(user);

        String accessToken = jwtService.generateAccessToken(user, ip);
        String refreshToken = jwtService.generateRefreshToken(user, ip);

        return revokePreviousAndSave(user, accessToken, refreshToken);
    }

    private User retrieveUser(String jwt) {

        String username = jwtService.extractSubject(jwt);
        return userService.loadUserByUsername(username);
    }
}
