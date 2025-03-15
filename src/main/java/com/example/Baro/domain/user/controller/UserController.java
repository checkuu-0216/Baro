package com.example.Baro.domain.user.controller;

import com.example.Baro.common.AuthUser;
import com.example.Baro.domain.user.dto.AdminRequestDto;
import com.example.Baro.domain.user.dto.AdminResponseDto;
import com.example.Baro.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/admin/users/roles")
    public ResponseEntity<AdminResponseDto> grantAdminRole(@AuthenticationPrincipal AuthUser authUser,
                                                           @RequestBody AdminRequestDto adminRequestDto
    ) {
        AdminResponseDto responseDto = userService.grantAdminRole(authUser,adminRequestDto);

        return ResponseEntity.ok(responseDto);
    }

}
