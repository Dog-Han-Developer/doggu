package com.doghandeveloper.doggu.Account.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Schema(description = "이메일 인증번호 전송 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthEmailSendRequest {
    @Schema(description = "인증번호 전송용 이메일", example = "abcd1234@naver.com")
    @Email(message = "이메일 형식이 아닙니다.")
    @Length(max = 100, message = "이메일은 100자 이하로 입력해주세요.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @Builder
    public AuthEmailSendRequest(String email) {
        this.email = email;
    }
}
