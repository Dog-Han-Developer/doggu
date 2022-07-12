package com.doghandeveloper.doggu.common.exception.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "EN_001", "올바르지 않은 요청 값입니다."),

    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST.value(), "AU_001", "이미 존재하는 이메일입니다."),
    DUPLICATED_USERNAME(HttpStatus.BAD_REQUEST.value(), "AU_002", "이미 존재하는 사용자 이름입니다."),

    EMAIL_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ES_001", "인증번호 이메일 전송이 실패했습니다.");
    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
