package com.doghandeveloper.doggu.Account.controller;

import com.doghandeveloper.doggu.Account.dto.request.AuthEmailSendRequest;
import com.doghandeveloper.doggu.Account.dto.request.AuthEmailVerifyRequest;
import com.doghandeveloper.doggu.Account.service.AccountService;
import com.doghandeveloper.doggu.common.exception.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/email")
    @Operation(summary = "이메일 중복체크 및 인증번호 이메일 전송", description = "이메일의 중복을 체크하고, 인증번호를 담은 이메일을 전송합니다.", responses = {
        @ApiResponse(responseCode = "200", description = "인증번호 이메일 전송 성공"),
        @ApiResponse(responseCode = "400", description = "이메일 중복 혹은 인증번호 이메일 전송 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> sendAuthenticationEmail(@Valid @RequestBody AuthEmailSendRequest emailAuthRequest) {
        accountService.sendEmail(emailAuthRequest.getEmail());
        log.info("이메일 전송 성공: {}", emailAuthRequest.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/verify")
    @Operation(summary = "이메일 인증번호 확인", description = "이메일로 전송받은 인증번호를 확인합니다.", responses = {
        @ApiResponse(responseCode = "200", description = "인증번호 확인 성공"),
        @ApiResponse(responseCode = "400", description = "인증번호 확인 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> verifyEmailAuthentication(@Valid @RequestBody AuthEmailVerifyRequest authEmailVerifyRequest) {
        accountService.verifyAuthenticationCode(authEmailVerifyRequest.getEmail(), authEmailVerifyRequest.getAuthenticationCode());
        log.info("이메일 인증번호 확인 성공: {}", authEmailVerifyRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/duplicate/{username}")
    @Operation(summary = "사용자 이름 중복 체크", description = "사용자 이름의 중복을 체크합니다.", responses = {
        @ApiResponse(responseCode = "200", description = "사용자 이름 중복 체크 성공"),
        @ApiResponse(responseCode = "400", description = "사용자 이름 중복 체크 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> verifyDuplicateUsername(
        @Pattern(regexp = "^[a-z0-9_\\.]*$", message = "사용자 이름은 영어(소문자), 숫자, _, .만 사용 가능합니다.")
        @PathVariable String username
    ) {
        accountService.verifyDuplicateUsername(username);
        log.info("사용자 이름 중복 체크 성공: {}", username);
        return ResponseEntity.ok().build();
    }
}
