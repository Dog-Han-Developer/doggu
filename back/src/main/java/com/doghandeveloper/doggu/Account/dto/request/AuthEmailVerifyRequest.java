package com.doghandeveloper.doggu.Account.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Schema(description = "이메일 인증번호 전송 요청")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthEmailVerifyRequest {
    @Schema(description = "인증번호 전송용 이메일", example = "abcd1234@naver.com")
    @Email(message = "이메일 형식이 아닙니다.")
    @Length(max = 100, message = "이메일은 100자 이하로 입력해주세요.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @Schema(description = "인증번호", example = "AAAA0000-00AA-AA00-A0A0-AA00AA00AA00")
    @Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$",message = "잘못된 인증번호입니다.")
    @NotBlank(message = "인증번호는 필수입니다.")
    private String authenticationCode;

    @Builder
    public AuthEmailVerifyRequest(String email, String authenticationCode) {
        this.email = email;
        this.authenticationCode = authenticationCode;
    }
}
