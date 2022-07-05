package com.doghandeveloper.doggu.common.exception.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
public class ErrorResponse {

    private final String message;
    private final String code;
    private final List<CustomFieldError> errors;

    // @Valid처럼 여러 에러가 동시에 발생할 경우
    private ErrorResponse(ErrorCode errorCode, List<CustomFieldError> errors) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.errors = new ArrayList<>(errors);
    }

    private ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(errorCode, CustomFieldError.of(bindingResult));
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode, CustomFieldError.of(message));
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    @Getter
    public static class CustomFieldError {

        private final String field;
        private final Object value;
        private final String reason;

        private CustomFieldError(String field, Object value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<CustomFieldError> of(BindingResult bindingResult) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                .map(CustomFieldError::of)
                .collect(Collectors.toList());
        }

        public static CustomFieldError of(FieldError fieldError) {
            return new CustomFieldError(
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage()
            );
        }

        public static List<CustomFieldError> of(String message) {
            List<CustomFieldError> customFieldErrors = new ArrayList<>();

            customFieldErrors.add(new CustomFieldError(null, null, message));
            return customFieldErrors;
        }
    }
}
