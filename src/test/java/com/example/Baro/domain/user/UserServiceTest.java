package com.example.Baro.domain.user;

import com.example.Baro.common.AuthUser;
import com.example.Baro.config.JwtUtil;
import com.example.Baro.domain.user.dto.AdminRequestDto;
import com.example.Baro.domain.user.dto.AdminResponseDto;
import com.example.Baro.domain.user.entity.User;
import com.example.Baro.domain.user.enums.UserRole;
import com.example.Baro.domain.user.exception.AccessDeniedException;
import com.example.Baro.domain.user.exception.UserNotFoundException;
import com.example.Baro.domain.user.repository.UserRepository;
import com.example.Baro.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService userService;

    @Test
    void 권한변경_성공() {
        // Given: 관리자 권한을 가진 로그인 사용자와 관리자 권한을 부여할 대상 사용자
        AuthUser authUser = new AuthUser(1L, "adminUser", UserRole.ROLE_ADMIN);
        AdminRequestDto adminRequestDto = new AdminRequestDto(1L); // 대상 사용자의 ID
        User loggedUser = new User("adminUser", "password", "nickname", UserRole.ROLE_ADMIN);
        User targetUser = new User("targetUser", "password", "nickname2", UserRole.ROLE_USER);

        // When: 로그인 사용자와 대상 사용자의 mock 설정
        when(userRepository.findByUsername(authUser.getUserName())).thenReturn(Optional.of(loggedUser));
        when(userRepository.findById(adminRequestDto.getUserId())).thenReturn(Optional.of(targetUser));

        // When: grantAdminRole 메서드 호출
        AdminResponseDto response = userService.grantAdminRole(authUser, adminRequestDto);

        // Then: 응답이 예상대로 이루어지는지 확인
        assertNotNull(response);
        assertEquals("targetUser", response.getUsername());
        assertEquals("ADMIN", response.getRoles().get(0).getRole()); // 기대값을 "ADMIN"으로 수정
        verify(userRepository, times(1)).save(targetUser); // 권한이 변경되어 저장되어야 함
    }

    @Test
    void 권한없음_실패() {
        // Given: 관리자 권한을 가지고 있지 않은 로그인 사용자
        AuthUser authUser = new AuthUser(1L, "nonAdminUser", UserRole.ROLE_ADMIN);
        AdminRequestDto adminRequestDto = new AdminRequestDto(1L); // 대상 사용자의 ID
        User loggedUser = new User("nonAdminUser", "password", "nickname", UserRole.ROLE_USER); // 권한이 USER인 사용자
        User targetUser = new User("targetUser", "password", "nickname", UserRole.ROLE_USER);

        // When: 로그인 사용자와 대상 사용자의 mock 설정
        when(userRepository.findByUsername(authUser.getUserName())).thenReturn(Optional.of(loggedUser));

        // Then: AccessDeniedException이 던져지는지 확인
        assertThrows(AccessDeniedException.class, () -> userService.grantAdminRole(authUser, adminRequestDto));
        verify(userRepository, never()).save(any(User.class)); // 권한 변경이 없어야 함
    }

    @Test
    void 로그인사용자없음_실패() {
        // Given: 로그인 사용자가 존재하지 않는 경우
        AuthUser authUser = new AuthUser(1L, "nonExistentUser", UserRole.ROLE_ADMIN);
        AdminRequestDto adminRequestDto = new AdminRequestDto(1L); // 대상 사용자의 ID

        // When: 로그인 사용자 조회 시 빈 값 반환하도록 mock 설정
        when(userRepository.findByUsername(authUser.getUserName())).thenReturn(Optional.empty());

        // Then: RuntimeException이 던져지는지 확인
        assertThrows(RuntimeException.class, () -> userService.grantAdminRole(authUser, adminRequestDto));
    }

    @Test
    void 대상사용자없음_실패() {
        // Given: 로그인 사용자는 존재하지만 대상 사용자가 없는 경우
        AuthUser authUser = new AuthUser(1L, "adminUser", UserRole.ROLE_ADMIN);
        AdminRequestDto adminRequestDto = new AdminRequestDto(1L); // 대상 사용자의 ID
        User loggedUser = new User("adminUser", "password", "nickname",UserRole.ROLE_ADMIN);

        // When: 로그인 사용자는 정상, 대상 사용자는 없는 경우
        when(userRepository.findByUsername(authUser.getUserName())).thenReturn(Optional.of(loggedUser));
        when(userRepository.findById(adminRequestDto.getUserId())).thenReturn(Optional.empty());

        // Then: UserNotFoundException이 던져지는지 확인
        assertThrows(UserNotFoundException.class, () -> userService.grantAdminRole(authUser, adminRequestDto));
    }

}
