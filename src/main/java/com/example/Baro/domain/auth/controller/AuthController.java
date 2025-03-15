package com.example.Baro.domain.auth.controller;

import com.example.Baro.domain.auth.Service.AuthService;
import com.example.Baro.domain.auth.dto.requestDto.LoginRequestDto;
import com.example.Baro.domain.auth.dto.requestDto.SignupRequestDto;
import com.example.Baro.domain.auth.dto.responseDto.LoginResponseDto;
import com.example.Baro.domain.auth.dto.responseDto.SignupResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원가입/로그인 관리기능")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    public ResponseEntity<SignupResponseDto> signup(@Valid
                                                    @RequestBody
                                                    @Parameter(description = "회원정보 입력")
                                                    SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    public ResponseEntity<LoginResponseDto> signin(@Valid
                                                   @RequestBody
                                                   @Parameter(description = "로그인 정보 입력")
                                                   LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


}
