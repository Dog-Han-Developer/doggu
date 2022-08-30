package com.doghandeveloper.doggu.Dog.controller;

import com.doghandeveloper.doggu.Dog.dto.request.DogAuthenticationRequest;
import com.doghandeveloper.doggu.Dog.dto.response.DogAuthenticationResponse;
import com.doghandeveloper.doggu.Dog.sevice.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dogs")
@Slf4j

public class DogController {

    private final DogService dogService;

    @PostMapping("/dog-authentication")
    @Operation(summary = "반려동물 인증", description = "사용자가 실제 반려동물을 키우는지 여부를 확인합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "반려동물 인증 성공"),
            @ApiResponse(responseCode = "400", description = "견주 이름 또는 동물등록번호 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<DogAuthenticationResponse> DogAuthentication(@Valid @RequestBody DogAuthenticationRequest dogAuthenticationRequest) throws IOException {
        DogAuthenticationResponse dogBody = dogService.sendDogInfo(dogAuthenticationRequest.getOwnerName(), dogAuthenticationRequest.getRegisterNumber());
        log.info("반려동물 인증 성공: {},{}", dogAuthenticationRequest.getOwnerName(), dogAuthenticationRequest.getRegisterNumber());

        if (dogBody == null) {
            return ResponseEntity.badRequest().build();
        }
        log.info(dogBody.toString());

        return ResponseEntity.ok().body(dogBody);
    }
}
