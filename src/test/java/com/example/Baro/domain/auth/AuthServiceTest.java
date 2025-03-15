package com.example.Baro.domain.auth;

import com.example.Baro.config.JwtUtil;
import com.example.Baro.domain.auth.Service.AuthService;
import com.example.Baro.domain.auth.dto.requestDto.LoginRequestDto;
import com.example.Baro.domain.auth.dto.requestDto.SignupRequestDto;
import com.example.Baro.domain.auth.dto.responseDto.LoginResponseDto;
import com.example.Baro.domain.auth.dto.responseDto.SignupResponseDto;
import com.example.Baro.domain.auth.exception.DuplicateUsernameException;
import com.example.Baro.domain.auth.exception.InvalidCredentialsException;
import com.example.Baro.domain.user.entity.User;
import com.example.Baro.domain.user.enums.UserRole;
import com.example.Baro.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    void 회원가입_성공() {

        SignupRequestDto signupRequestDto = new SignupRequestDto("newuser", "password123", "nickname");

        User newUser = new User("newuser", passwordEncoder.encode("password123"), "nickname", UserRole.ROLE_USER);
        when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(newUser);

        SignupResponseDto responseDto = authService.signup(signupRequestDto);

        assertNotNull(responseDto);
        assertEquals("newuser", responseDto.getUsername());
        assertEquals("nickname", responseDto.getNickname());
        assertEquals(1, responseDto.getRoles().size());
        assertEquals("USER", responseDto.getRoles().get(0).getRole());
    }

    @Test
    void 회원가입_중복이름_예외처리() {
        // Given: 이미 존재하는 사용자 이름
        String username = "existinguser";
        SignupRequestDto signupRequestDto = new SignupRequestDto(username, "password123", "nickname");

        // When: userRepository에서 이미 존재하는 사용자 이름을 찾으면 existsByUsername은 true를 반환
        when(userRepository.existsByUsername(username)).thenReturn(true);

        // Then: DuplicateUsernameException이 던져지는지 확인
        assertThrows(DuplicateUsernameException.class, () -> authService.signup(signupRequestDto));

        // verify: userRepository.save는 호출되지 않아야 함
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void 로그인_비밀번호_오류() {
        // Given
        String username = "testUser";
        String correctPassword = "password123";
        String wrongPassword = "wrongPassword123";
        User mockUser = new User(username, passwordEncoder.encode(correctPassword), "testNickname", UserRole.ROLE_USER);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // When & Then
        LoginRequestDto loginRequestDto = new LoginRequestDto(username, wrongPassword);
        assertThrows(InvalidCredentialsException.class, () -> authService.login(loginRequestDto));
        verify(userRepository, times(1)).findByUsername(username); // userRepository 호출 확인
    }

}
