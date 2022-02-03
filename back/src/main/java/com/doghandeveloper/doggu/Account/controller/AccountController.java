package com.doghandeveloper.doggu.Account.controller;

import com.doghandeveloper.doggu.Account.dto.request.EmailAuthRequest;
import com.doghandeveloper.doggu.Account.service.AccountService;
import com.doghandeveloper.doggu.common.exception.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/email")
    @Operation(summary = "인증번호 이메일 전송", description = "인증번호를 담은 이메일을 전송합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "인증번호 이메일 전송 성공"),
            @ApiResponse(responseCode = "400", description = "인증번호 이메일 전송 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> emailAuthentication(@Valid @RequestBody EmailAuthRequest emailAuthRequest) {
        accountService.sendEmail(emailAuthRequest.getEmail());
        log.info("이메일 전송 성공: {}", emailAuthRequest.getEmail());
        return ResponseEntity.ok().build();
    }
}
