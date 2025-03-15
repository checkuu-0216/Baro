package com.example.Baro.domain.auth.dto.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class SignupResponseDto {

    @Schema(description = "사용자 이름", example = "아무개")
    private String username;

    @Schema(description = "닉네임", example = "아무개가 원하는 별명")
    private String nickname;

    @Schema(description = "사용자 권한", example = "ROLE_ADMIN")
    private List<AuthorityResponse> roles;

    @Getter
    public static class AuthorityResponse {
        private final String role;

        public AuthorityResponse(String role) {
            this.role = role;
        }
    }
}
