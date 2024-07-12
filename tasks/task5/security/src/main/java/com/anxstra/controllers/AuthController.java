package com.anxstra.controllers;

import com.anxstra.dto.AuthResponse;
import com.anxstra.dto.UserLoginDto;
import com.anxstra.dto.UserRegisterDto;
import com.anxstra.jwtfilterspringbootstarter.dto.BaseResponseEntity;
import com.anxstra.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.anxstra.constants.PathConstants.LOGIN_PATH;
import static com.anxstra.constants.PathConstants.REFRESH_PATH;
import static com.anxstra.constants.PathConstants.REGISTER_PATH;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER_PATH)
    public ResponseEntity<BaseResponseEntity<Void>> register(@Valid @RequestBody UserRegisterDto user) {

        authService.register(user);

        return ResponseEntity.ok(BaseResponseEntity.ok());
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<BaseResponseEntity<AuthResponse>> login(@Valid @RequestBody UserLoginDto user,
                                                                  HttpServletRequest request) {

        AuthResponse response = authService.authenticate(user, request);

        return ResponseEntity.ok(BaseResponseEntity.ok(response));
    }

    @GetMapping(REFRESH_PATH)
    public ResponseEntity<BaseResponseEntity<AuthResponse>> refresh(HttpServletRequest request) {

        AuthResponse response = authService.refresh(request);

        return ResponseEntity.ok(BaseResponseEntity.ok(response));
    }
}
