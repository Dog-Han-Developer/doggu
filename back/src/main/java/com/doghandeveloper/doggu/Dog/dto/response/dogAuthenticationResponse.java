package com.doghandeveloper.doggu.Dog.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "반려견 인증 응답")
@Getter
public class dogAuthenticationResponse {

    @Schema(description = "동물등록증에 등록된 반려견 이름", example = "이요미")
    @NotNull
    @NotBlank
    private String dogName;

    @Schema(description = "동물등록증에 등록된 반려견 품종", example = "웰시코기 펨브로크")
    @NotNull
    @NotBlank
    private String dogKind;

    @Schema(description = "동물등록증에 등록된 반려견", example = "암컷")
    @NotNull
    @NotBlank
    private String dogSex;

}
