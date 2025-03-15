package com.example.Baro.domain.user.exception;

import com.example.Baro.common.exception.GlobalException;
import com.example.Baro.common.exception.GlobalExceptionConst;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException() {
        super(GlobalExceptionConst.NOT_FOUND_USER);
    }
}
