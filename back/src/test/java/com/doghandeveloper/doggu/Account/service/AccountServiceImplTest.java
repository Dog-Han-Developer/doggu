package com.doghandeveloper.doggu.Account.service;

import com.doghandeveloper.doggu.Account.repository.AccountRepository;
import com.doghandeveloper.doggu.common.exception.AuthException;
import com.doghandeveloper.doggu.common.utils.EmailSendUtil;
import com.doghandeveloper.doggu.common.utils.RedisUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private EmailSendUtil emailSendUtil;

    @Mock
    private RedisUtil redisUtil;

    @Test
    @DisplayName("인증메일을 성공적으로 보낸다.")
    void sendEmail() {
        emailSendUtil.sendEmail(anyString(),anyString(),anyString());
        redisUtil.setDataExpire(anyString(),anyString(),anyLong());

        verify(emailSendUtil).sendEmail(anyString(),anyString(),anyString());
        verify(redisUtil).setDataExpire(anyString(),anyString(),anyLong());
    }

    @Test
    @DisplayName("중복된 이메일로 인증메일을 보낼 수 없다.")
    void sendEmailToDuplicatedEmail() {
        String email = "doggu@gmail.com";

        when(accountRepository.existsByEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> accountService.sendEmail(email))
                .isInstanceOf(AuthException.class)
                .hasMessageContainingAll("존재하는 이메일");
    }
}