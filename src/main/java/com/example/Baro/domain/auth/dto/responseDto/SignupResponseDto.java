package com.example.Baro.domain.auth.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public final class SignupResponseDto {

    private String username;

    private String nickname;

    private List<AuthorityResponse> roles;

    @Getter
    public static class AuthorityResponse {
        private final String role;

        public AuthorityResponse(String role) {
            this.role = role;
        }
    }
}
