package com.doghandeveloper.doggu.Account.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class EmailAuthRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("올바른 이메일로 인증 요청을 보낸다.")
    void emailAuthRequestWithCorrectEmail() {
        String email = "abcd1234@gmail.com";
        EmailAuthRequest emailAuthRequest = EmailAuthRequest.builder().email(email).build();

        Set<ConstraintViolation<EmailAuthRequest>> validate = validator.validate(emailAuthRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).size().isEqualTo(0);
    }

    @Test
    @DisplayName("잘못된 형식의 이메일로 인증 요청을 보낼 수 없다.")
    void emailAuthRequestWithWrongEmail() {
        String email = " ";
        EmailAuthRequest emailAuthRequest = EmailAuthRequest.builder().email(email).build();

        Set<ConstraintViolation<EmailAuthRequest>> validate = validator.validate(emailAuthRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).contains("이메일 형식이 아닙니다.", "이메일은 필수입니다.");
    }

    @Test
    @DisplayName("너무 긴 이메일로 인증 요청을 보낼 수 없다.")
    void emailAuthRequestWithTooLongEmail() {
        String email = "abcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyz@gmail.com";
        EmailAuthRequest emailAuthRequest = EmailAuthRequest.builder().email(email).build();

        Set<ConstraintViolation<EmailAuthRequest>> validate = validator.validate(emailAuthRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).contains("이메일은 100자 이하로 입력해주세요.");
    }
}