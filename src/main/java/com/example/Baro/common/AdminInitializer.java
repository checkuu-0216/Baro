package com.example.Baro.common;

import com.example.Baro.domain.user.enums.UserRole;
import com.example.Baro.domain.user.entity.User;
import com.example.Baro.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //관리자 권한 부여 서비스를 위한 최초 관리자계정 생성
    @Override
    public void run(String... args) {
        // DB에 관리자 계정이 있는지 확인
        boolean adminExists = userRepository.existsByUserRole(UserRole.ROLE_ADMIN);

        if (!adminExists) {
            // 관리자 계정 생성
            User admin = new User(
                    "admin", // 기본 관리자 ID
                    passwordEncoder.encode("admin123"), // 기본 비밀번호 (실제 운영에서는 변경 필요!)
                    "SuperAdmin", // 닉네임
                    UserRole.ROLE_ADMIN // 관리자 권한 부여
            );
            userRepository.save(admin);
            System.out.println("최초 관리자 계정 생성 완료! (ID: admin, PW: admin123)");
        }
    }
}
