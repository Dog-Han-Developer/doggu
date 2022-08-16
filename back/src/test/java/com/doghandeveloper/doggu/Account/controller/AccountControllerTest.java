package com.doghandeveloper.doggu.Account.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.doghandeveloper.doggu.Account.controller.docs.AccountDocumentation;
import com.doghandeveloper.doggu.Account.dto.request.AuthEmailSendRequest;
import com.doghandeveloper.doggu.Account.dto.request.AuthEmailVerifyRequest;
import com.doghandeveloper.doggu.Account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
@AutoConfigureRestDocs
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("인증번호 이메일 발송")
    void sendAuthenticationEmail() throws Exception {
        AuthEmailSendRequest authEmailSendRequest = AuthEmailSendRequest.builder()
            .email("doggu@gmail.com")
            .build();

        mockMvc.perform(post("/accounts/email")
                .content(objectMapper.writeValueAsString(authEmailSendRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
            )
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(AccountDocumentation.sendAuthenticationEmail());
    }

    @Test
    @DisplayName("이메일 인증번호 확인")
    void verifyAuthenticationCode() throws Exception {
        AuthEmailVerifyRequest authEmailVerifyRequest = AuthEmailVerifyRequest.builder()
            .email("doggu@gmail.com")
            .authenticationCode("2b0a8a4d-a27f-4d01-b031-2a003cc4c039")
            .build();

        mockMvc.perform(post("/accounts/email/verify")
                .content(objectMapper.writeValueAsString(authEmailVerifyRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
            )
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(AccountDocumentation.verifyAuthenticationCode());
    }

    @Test
    @DisplayName("사용자 이름 중복 체크")
    void verifyDuplicateUsername() throws Exception {
        String username = "doggu_love";
        mockMvc.perform(RestDocumentationRequestBuilders.get("/accounts/duplicate/{username}", username)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
            )
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(AccountDocumentation.verifyDuplicateUserName());
    }
}
