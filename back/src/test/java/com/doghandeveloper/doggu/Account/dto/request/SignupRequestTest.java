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

class SignupRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("올바른 요청 값으로 회원가입을 요청한다.")
    void withCorrectValue() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email("abcd1234@gmail.com")
            .username("doggu_love")
            .password("@password134")
            .owner("DOG_OWNER")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).size().isEqualTo(0);
    }

    @Test
    @DisplayName("모든 값은 필수 값이다.")
    void withBlankValue() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email(" ")
            .username(" ")
            .password(" ")
            .owner(" ")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).contains("이메일은 필수입니다.", "사용자 이름은 필수입니다.", "비밀번호는 필수입니다.", "견주 여부는 필수입니다.");
    }

    @Test
    @DisplayName("잘못된 형식의 이메일로 회원가입을 할 수 없다.")
    void withWrongEmail() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email("abcd1234#gmail.com")
            .username("doggu_love")
            .password("@password134")
            .owner("DOG_OWNER")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).contains("이메일 형식이 아닙니다.");
    }

    @Test
    @DisplayName("잘못된 길이의 이메일로 회원가입을 할 수 없다.")
    void withTooLongEmail() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email("abcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyzabcdefghijklmnopqlstuvwxyz@gmail.com")
            .username("doggu_love")
            .password("@password134")
            .owner("DOG_OWNER")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).contains("이메일은 100자 이하로 입력해주세요.");
    }

    @Test
    @DisplayName("잘못된 형식의 사용자 이름으로 회원가입을 할 수 없다.")
    void withWrongUsername() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email("abcd1234@gmail.com")
            .username("doggu#love")
            .password("@password134")
            .owner("DOG_OWNER")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).contains("사용자 이름은 영어(소문자), 숫자, _, .만 사용 가능합니다.");
    }

    @Test
    @DisplayName("잘못된 길이의 사용자 이름으로 회원가입을 할 수 없다.")
    void withWrongLengthUsername() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email("abcd1234@gmail.com")
            .username("doggu_love.1234567890")
            .password("@password134")
            .owner("DOG_OWNER")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).contains("사용자 이름은 2자이상 16자이하로 입력해주세요.");
    }

    @Test
    @DisplayName("잘못된 형식의 비밀번호로 회원가입을 할 수 없다.")
    void withWrongPassword() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email("abcd1234@gmail.com")
            .username("doggu_love")
            .password("rd134")
            .owner("DOG_OWNER")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).contains("비밀번호는 영어(대소문자무관), 특수문자, 숫자 3종류의 조합이어야합니다.");
    }

    @Test
    @DisplayName("잘못된 길이 비밀번호로 회원가입을 할 수 없다.")
    void withWrongLengthPassword() {
        SignupRequest signupRequest = SignupRequest.builder()
            .email("abcd1234@gmail.com")
            .username("doggu_love")
            .password("@password134567890")
            .owner("DOG_OWNER")
            .build();

        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest);
        List<String> messages = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        System.out.println(messages);

        Assertions.assertThat(messages).contains("비밀번호는 8자이상 16자이하로 입력해주세요.");
    }
}
