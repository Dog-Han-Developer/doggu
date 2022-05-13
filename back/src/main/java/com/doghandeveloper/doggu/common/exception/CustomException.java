package com.doghandeveloper.doggu.common.exception;

import com.doghandeveloper.doggu.common.exception.dto.ErrorCode;

public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
