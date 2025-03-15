package com.example.Baro.domain.user.service;

import com.example.Baro.common.AuthUser;
import com.example.Baro.config.JwtUtil;
import com.example.Baro.domain.user.dto.AdminRequestDto;
import com.example.Baro.domain.user.dto.AdminResponseDto;
import com.example.Baro.domain.user.entity.User;
import com.example.Baro.domain.user.enums.UserRole;
import com.example.Baro.domain.user.exception.AccessDeniedException;
import com.example.Baro.domain.user.exception.UserNotFoundException;
import com.example.Baro.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AdminResponseDto grantAdminRole(AuthUser authUser, AdminRequestDto adminRequestDto) {

        // 인증된 사용자의 ID를 통해 권한 확인
        User loggedUser = userRepository.findByUsername(authUser.getUserName())
                .orElseThrow(() -> new RuntimeException("현재 로그인한 사용자를 찾을 수 없습니다."));

        // 로그인한 사용자가 ADMIN 권한을 가지고 있는지 확인
        if (!loggedUser.getUserRole().equals(UserRole.ROLE_ADMIN)) {
            throw new AccessDeniedException();
        }

        // 대상 사용자의 정보를 가져옵니다.
        User targetUser = userRepository.findById(adminRequestDto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        // 사용자의 권한을 관리자 권한으로 변경
        targetUser.updateRole(UserRole.ROLE_ADMIN);
        userRepository.save(targetUser);

        // 응답 DTO 변환
        return new AdminResponseDto(
                targetUser.getUsername(),
                targetUser.getNickname(),
                List.of(new AdminResponseDto.RoleResponseDto(targetUser.getUserRole().getAuthorityName()))
        );
    }
}
