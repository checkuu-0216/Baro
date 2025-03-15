package com.example.Baro.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AdminResponseDto {

    private String username;
    private String nickname;
    private List<RoleResponseDto> roles;

    @Getter
    public static class RoleResponseDto {
        private final String role;

        public RoleResponseDto(String role) {
            this.role = role;
        }
    }
}
