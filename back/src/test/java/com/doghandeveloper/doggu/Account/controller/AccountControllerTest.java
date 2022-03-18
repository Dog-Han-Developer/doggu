package com.doghandeveloper.doggu.Account.controller;

import com.doghandeveloper.doggu.Account.controller.docs.AccountDocumentation;
import com.doghandeveloper.doggu.Account.dto.request.EmailAuthRequest;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @DisplayName("인증번호 이메일 발송 성공")
    void emailAuthenticationSuccess() throws Exception {
        EmailAuthRequest emailAuthRequest = EmailAuthRequest.builder()
                .email("doggu@gmail.com")
                .build();

        mockMvc.perform(post("/accounts/email")
                        .content(objectMapper.writeValueAsString(emailAuthRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(AccountDocumentation.emailAuthentication());
    }

}