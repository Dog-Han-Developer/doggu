package com.doghandeveloper.doggu.Dog.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "반려견 인증 응답")
@Getter
@Setter
@Builder
public class DogAuthenticationResponse {

    @Schema(description = "동물등록증에 등록된 반려견 이름", example = "이요미")
    private String dogName;

    @Schema(description = "동물등록증에 등록된 반려견 품종", example = "웰시코기 펨브로크")
    private String dogKind;

    @Schema(description = "동물등록증에 등록된 반려견", example = "암컷")
    private Enum dogSex;

    public static DogAuthenticationResponse toDogAuthenticationResponse(String dogName, String dogKind, Enum dogSex){
        return DogAuthenticationResponse.builder()
                .dogName(dogName)
                .dogKind(dogKind)
                .dogSex(dogSex)
                .build();
    }
}
