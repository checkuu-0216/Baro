package com.example.Baro.domain.user.exception;

import com.example.Baro.common.exception.GlobalException;
import com.example.Baro.common.exception.GlobalExceptionConst;

public class AccessDeniedException extends GlobalException {
    public AccessDeniedException() {
        super(GlobalExceptionConst.ACCESS_DENIED);
    }
}
