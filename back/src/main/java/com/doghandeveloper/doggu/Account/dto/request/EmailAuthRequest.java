package com.doghandeveloper.doggu.Account.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Schema(description = "이메일 인증 번호 전송 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAuthRequest {
    @Schema(description = "인증 번호 전송용 이메일", example = "abcd1234@naver.com")
    @Email
    @Length(max = 100, message = "이메일은 100자 이하로 입력해주세요.")
    @NotBlank
    private String email;
}
