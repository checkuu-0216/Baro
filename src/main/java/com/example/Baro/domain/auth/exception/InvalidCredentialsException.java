package com.example.Baro.domain.auth.exception;

import com.example.Baro.common.exception.GlobalException;
import com.example.Baro.common.exception.GlobalExceptionConst;

public class InvalidCredentialsException extends GlobalException {
    public InvalidCredentialsException() {
        super(GlobalExceptionConst.INVALID_CREDENTIALS);
    }
}
