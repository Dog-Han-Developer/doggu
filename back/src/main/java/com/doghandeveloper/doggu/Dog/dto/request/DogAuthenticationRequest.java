package com.doghandeveloper.doggu.Dog.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Schema(description = "반려견 인증 요청")
@Getter
public class DogAuthenticationRequest {

    @Schema(description = "동물등록증에 등록된 소유자(견주) 이름", example = "이수민")
    @Length(min = 2, max = 100, message = "소유자(견주) 이름은 2 ~ 100자 사이로 입력해주세요.")
    @Pattern(regexp = "[가-힣a-zA-Z]", message = "소유자(견주) 이름은 한글 or 영어로만 입력해주세요.")
    @NotBlank
    private String ownerName;

    @Schema(description = "동물등록증에 등록된 반려견 등록번호", example = "999999999999999")
    @Length(min = 15, max = 15, message = "등록번호는 15자로 입력해주세요.(국가번호 3자리 + 일련번호 12자리)")
    @NotBlank
    private String registerNumber;

}
