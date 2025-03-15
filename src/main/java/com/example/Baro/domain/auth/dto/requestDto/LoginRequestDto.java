package com.example.Baro.domain.auth.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @Schema(description = "사용자 이름", example = "아무개")
    private String username;
    @Schema(description = "비밀번호", example = "12345")
    private String password;
}
