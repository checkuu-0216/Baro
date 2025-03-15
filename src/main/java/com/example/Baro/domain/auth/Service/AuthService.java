package com.example.Baro.domain.auth.Service;

import com.example.Baro.config.JwtUtil;
import com.example.Baro.domain.auth.dto.requestDto.LoginRequestDto;
import com.example.Baro.domain.auth.dto.requestDto.SignupRequestDto;
import com.example.Baro.domain.auth.dto.responseDto.LoginResponseDto;
import com.example.Baro.domain.auth.dto.responseDto.SignupResponseDto;
import com.example.Baro.domain.auth.exception.DuplicateUsernameException;
import com.example.Baro.domain.auth.exception.InvalidCredentialsException;
import com.example.Baro.domain.user.entity.User;
import com.example.Baro.domain.user.enums.UserRole;
import com.example.Baro.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        // 사용자 이름 중복 확인
        checkUsernameExists(signupRequestDto.getUsername());

        // Password Encoding
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        User newUser = new User(
                signupRequestDto.getUsername(),
                encodedPassword,
                signupRequestDto.getNickname(),
                UserRole.ROLE_USER
        );

        // UserRole -> AuthorityResponse 로 매핑
        List<SignupResponseDto.AuthorityResponse> authorityResponses = List.of(
                new SignupResponseDto.AuthorityResponse(newUser.getUserRole().getAuthorityName())
        );

        // DB 저장
        User savedUser = userRepository.save(newUser);

        return new SignupResponseDto(
                savedUser.getUsername(),
                savedUser.getNickname(),
                authorityResponses
        );
    }

    public LoginResponseDto login(@Valid LoginRequestDto loginRequestDto) {
        // 사용자 확인
        User user = validateCredential(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        String bearerToken = jwtUtil.createAccessToken(user.getId(), user.getUsername(), user.getUserRole());

        return new LoginResponseDto(bearerToken);
    }

    private void checkUsernameExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException();
        }
    }

    private User validateCredential(String username, String rawPassword) {
        // 사용자 존재 여부 확인
        Optional<User> user = userRepository.findByUsername(username);

        // 비밀번호 검증
        if (!passwordEncoder.matches(rawPassword, user.get().getPassword())) {
            throw new InvalidCredentialsException();  // 잘못된 아이디/비밀번호일 때 예외 처리
        }

        return user.orElse(null);  // 사용자 존재하고 비밀번호도 일치하면 사용자 객체 반환
    }

}
