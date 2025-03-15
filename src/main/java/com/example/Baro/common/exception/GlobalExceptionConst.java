package com.example.Baro.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalExceptionConst {

    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 가입된 사용자입니다."),
    INVALID_CREDENTIALS(HttpStatus.NOT_FOUND, "아이디 또는 비밀번호가 올바르지 않습니다."),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED,"관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND,"사용자를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
