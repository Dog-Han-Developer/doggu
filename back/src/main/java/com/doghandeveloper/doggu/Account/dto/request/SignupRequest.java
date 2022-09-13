package com.doghandeveloper.doggu.Account.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Schema(description = "회원 가입 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupRequest {
    @Schema(description = "이메일", example = "abcd1234@naver.com")
    @Email(message = "이메일 형식이 아닙니다.")
    @Length(max = 100, message = "이메일은 100자 이하로 입력해주세요.")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @Schema(description = "사용자 이름", example = "doggu_love.82")
    @Pattern(regexp = "^[a-z0-9_\\.]*$", message = "사용자 이름은 영어(소문자), 숫자, _, .만 사용 가능합니다.")
    @Length(min = 2, max = 16, message = "사용자 이름은 2자이상 16자이하로 입력해주세요.")
    @NotBlank(message = "사용자 이름은 필수입니다.")
    private String username;

    @Schema(description = "비밀번호", example = "@password1004!")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[#?!@$%^&*+=-]).{8,16}$", message = "비밀번호는 영어(대소문자무관), 특수문자, 숫자 3종류의 조합이어야합니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자이상 16자이하로 입력해주세요.")
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Schema(description = "견주 여부", example = "DOG_OWNER")
    @NotBlank(message = "견주 여부는 필수입니다.")
    private String owner;

    @Builder
    public SignupRequest(String email, String username, String password, String owner) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.owner = owner;
    }
}
