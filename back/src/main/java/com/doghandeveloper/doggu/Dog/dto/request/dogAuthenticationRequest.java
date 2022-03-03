package com.doghandeveloper.doggu.Dog.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "반려견 인증 요청")
@Getter
public class dogAuthenticationRequest {

    @Schema(description = "동물등록증에 등록된 소유자(견주) 이름", example = "이수민")
    @NotNull
    @NotBlank
    private String ownerName;

    @Schema(description = "동물등록증에 등록된 반려견 등록번호", example = "999999999999999")
    @Length(min = 15, max = 15, message = "등록번호는 15자로 구성되어있습니다.(국가번호 3자리 + 일련번호 12자리)")
    @NotNull
    @NotBlank
    private String registerNumber;

}
