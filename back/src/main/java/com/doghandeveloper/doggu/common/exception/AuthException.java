package com.doghandeveloper.doggu.common.exception;

import com.doghandeveloper.doggu.common.exception.dto.ErrorCode;

public class AuthException extends CustomException{
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
