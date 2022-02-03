package com.doghandeveloper.doggu.common.exception.handler;

import com.doghandeveloper.doggu.common.exception.dto.ErrorCode;
import com.doghandeveloper.doggu.common.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        log.error("MethodArgumentNotValidException: {}", ErrorCode.INVALID_INPUT_VALUE.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MailException.class)
    private ResponseEntity<ErrorResponse> handleMailException(MailException e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.EMAIL_SEND_FAIL);
        log.error("MailException: {}", ErrorCode.EMAIL_SEND_FAIL.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
