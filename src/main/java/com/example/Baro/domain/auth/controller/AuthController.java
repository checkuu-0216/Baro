package com.example.Baro.domain.auth.controller;

import com.example.Baro.domain.auth.Service.AuthService;
import com.example.Baro.domain.auth.dto.requestDto.LoginRequestDto;
import com.example.Baro.domain.auth.dto.requestDto.SignupRequestDto;
import com.example.Baro.domain.auth.dto.responseDto.LoginResponseDto;
import com.example.Baro.domain.auth.dto.responseDto.SignupResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@Valid
                                                    @RequestBody
                                                    SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> signin(@Valid
                                                   @RequestBody
                                                   LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


}
