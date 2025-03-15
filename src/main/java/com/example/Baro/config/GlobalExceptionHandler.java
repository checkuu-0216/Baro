package com.example.Baro.config;

import com.example.Baro.common.exception.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(GlobalException ex) {
        // 동적 HashMap 사용
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("code", ex.getCode()); // 에러 코드 먼저
        errorDetails.put("message", ex.getMessage()); // 메시지 뒤에
        errorResponse.put("error", errorDetails);

        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }

}
