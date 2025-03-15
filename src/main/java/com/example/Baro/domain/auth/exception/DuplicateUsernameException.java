package com.example.Baro.domain.auth.exception;

import com.example.Baro.common.exception.GlobalException;
import com.example.Baro.common.exception.GlobalExceptionConst;

public class DuplicateUsernameException extends GlobalException {
    public DuplicateUsernameException() {
        super(GlobalExceptionConst.USER_ALREADY_EXISTS);
    }
}
