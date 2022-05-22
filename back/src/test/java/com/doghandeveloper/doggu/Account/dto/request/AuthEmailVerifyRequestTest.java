package com.doghandeveloper.doggu.Account.dto.request;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthEmailVerifyRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("올바른 이메일 및 인증번호로 인증번호 확인을 요청한다.")
    void withCorrectEmailAndAuthenticationCode() {
        AuthEmailVerifyRequest authEmailVerifyRequest = AuthEmailVerifyRequest.builder()
            .email("doggu@gmail.com")
            .authenticationCode("2b0a8a4d-a27f-4d01-b031-2a003cc4c039")
            .build();

        Set<ConstraintViolation<AuthEmailVerifyRequest>> validate = validator.validate(authEmailVerifyRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).size().isEqualTo(0);
    }

    @Test
    @DisplayName("이메일, 인증번호는 필수다.")
    void withBlank(){
        AuthEmailVerifyRequest authEmailVerifyRequest = AuthEmailVerifyRequest.builder()
            .email(" ")
            .authenticationCode(" ")
            .build();

        Set<ConstraintViolation<AuthEmailVerifyRequest>> validate = validator.validate(authEmailVerifyRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).contains("이메일은 필수입니다.", "인증번호는 필수입니다.");
    }

    @Test
    @DisplayName("잘못된 형식의 이메일로 인증번호 확인을 요청할 수 없다.")
    void withWrongEmail() {
        AuthEmailVerifyRequest authEmailVerifyRequest = AuthEmailVerifyRequest.builder()
            .email("abcd#dda.com")
            .authenticationCode("2b0a8a4d-a27f-4d01-b031-2a003cc4c039")
            .build();

        Set<ConstraintViolation<AuthEmailVerifyRequest>> validate = validator.validate(authEmailVerifyRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).contains("이메일 형식이 아닙니다.");
    }

    @Test
    @DisplayName("너무 긴 이메일로 인증번호 확인을 요청할 수 없다.")
    void withTooLongEmail() {
        AuthEmailVerifyRequest authEmailVerifyRequest = AuthEmailVerifyRequest.builder()
            .email("abcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyz@gmail.com")
            .authenticationCode("2b0a8a4d-a27f-4d01-b031-2a003cc4c039")
            .build();

        Set<ConstraintViolation<AuthEmailVerifyRequest>> validate = validator.validate(authEmailVerifyRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).contains("이메일은 100자 이하로 입력해주세요.");
    }

    @Test
    @DisplayName("잘못된 형식의 인증번호로 인증번호 확인을 요청할 수 없다.")
    void withWrongAuthenticationCode(){
        AuthEmailVerifyRequest authEmailVerifyRequest = AuthEmailVerifyRequest.builder()
            .email("doggu@gmail.com")
            .authenticationCode("doggu-is-love")
            .build();

        Set<ConstraintViolation<AuthEmailVerifyRequest>> validate = validator.validate(authEmailVerifyRequest);

        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        Assertions.assertThat(messages).contains("잘못된 인증번호입니다.");
    }
}
