package com.example.Baro.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlobalException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String code;     // 에러 코드 (ex: USER_ALREADY_EXISTS)
    private final String message;  // 에러 메시지 (ex: "이미 가입된 사용자입니다.")

    public GlobalException(GlobalExceptionConst globalExceptionConst) {
        super(globalExceptionConst.getMessage()); // 기본 Exception 메시지 설정
        this.httpStatus = globalExceptionConst.getHttpStatus();
        this.code = globalExceptionConst.name();  // Enum의 이름을 code로 사용
        this.message = globalExceptionConst.getMessage();
    }

}
