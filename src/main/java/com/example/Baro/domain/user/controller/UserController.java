package com.example.Baro.domain.user.controller;

import com.example.Baro.common.AuthUser;
import com.example.Baro.domain.user.dto.AdminRequestDto;
import com.example.Baro.domain.user.dto.AdminResponseDto;
import com.example.Baro.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "유저 권한 변경")
public class UserController {

    private final UserService userService;

    @PatchMapping("/admin/users/roles")
    @Operation(summary = "권한 변경")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    public ResponseEntity<AdminResponseDto> grantAdminRole(@AuthenticationPrincipal AuthUser authUser,
                                                           @RequestBody
                                                           @Parameter(description = "변경할 유저 정보")
                                                           AdminRequestDto adminRequestDto) {
        AdminResponseDto responseDto = userService.grantAdminRole(authUser, adminRequestDto);

        return ResponseEntity.ok(responseDto);
    }

}
