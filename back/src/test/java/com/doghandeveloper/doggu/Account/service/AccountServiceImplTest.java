package com.doghandeveloper.doggu.Account.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.doghandeveloper.doggu.Account.domain.Account;
import com.doghandeveloper.doggu.Account.domain.Owner;
import com.doghandeveloper.doggu.Account.repository.AccountRepository;
import com.doghandeveloper.doggu.common.exception.AuthException;
import com.doghandeveloper.doggu.common.exception.dto.ErrorCode;
import com.doghandeveloper.doggu.common.utils.EmailSendUtil;
import com.doghandeveloper.doggu.common.utils.RedisUtil;
import java.security.InvalidParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        String email = "doggu@gmail.com";

        accountService.sendEmail(email);

        verify(emailSendUtil).sendEmail(anyString(), anyString(), anyString());
        verify(redisUtil).setDataExpire(anyString(), anyString(), anyLong());
    }

    @Test
    @DisplayName("중복된 이메일로 인증메일을 보낼 수 없다.")
    void sendEmailToDuplicatedEmail() {
        String email = "doggu@gmail.com";

        when(accountRepository.existsByEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> accountService.sendEmail(email))
            .isInstanceOf(AuthException.class)
            .hasMessageContainingAll(ErrorCode.DUPLICATED_EMAIL.getMessage());
    }

    @Test
    @DisplayName("인증번호가 올바른지 확인한다.")
    void verifyAuthenticationCode() {
        String email = "doggu@gmail.com";
        String authenticationCode = "2b0a8a4d-a27f-4d01-b031-2a003cc4c039";

        when(redisUtil.getData(email)).thenReturn(authenticationCode);

        assertDoesNotThrow(() -> accountService.verifyAuthenticationCode(email, authenticationCode));
    }

    @Test
    @DisplayName("올바르지 않은 이메일은 확인할 수 없다.")
    void verifyAuthenticationCodeWithWrongEmail() {
        String email = "wrongdoggu@gmail.com";
        String authenticationCode = "2b0a8a4d-a27f-4d01-b031-2a003cc4c039";

        when(redisUtil.getData(email)).thenReturn(null);

        assertThatThrownBy(() -> accountService.verifyAuthenticationCode(email, authenticationCode))
            .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    @DisplayName("올바르지 않은 인증번호는 확인할 수 없다.")
    void verifyAuthenticationCodeWithWrongAuthenticationCode() {
        String email = "doggu@gmail.com";
        String authenticationCode = "2b0a8a4d-a27f-4d01-b031-2a003cc4c039";
        String savedAuthenticationCode = "2b0a8a4b-b27b-4d01-b031-2a003cc4c039";

        when(redisUtil.getData(email)).thenReturn(savedAuthenticationCode);

        assertThatThrownBy(() -> accountService.verifyAuthenticationCode(email, authenticationCode))
            .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    @DisplayName("사용자 이름이 중복되지 않았는지 확인한다.")
    void verifyDuplicateUsername() {
        String username = "doggu_love";

        when(accountRepository.existsByUsername(username)).thenReturn(false);

        assertDoesNotThrow(() -> accountService.verifyDuplicateUsername(username));
    }

    @Test
    @DisplayName("회원정보를 성공적으로 저장한다.")
    void save() {
        Account account = Account.builder()
            .email("abcd1234@gmail.com")
            .username("doggu_love")
            .password("@password134")
            .owner(Owner.DOG_OWNER)
            .build();

        accountService.save(account);

        verify(accountRepository).save(account);
    }

    @Test
    @DisplayName("중복된 이메일을 가진 회원정보는 저장할 수 없다.")
    void saveWithDuplicateEmail() {
        String email = "abcd1234@gmail.com";
        Account account = Account.builder()
            .email(email)
            .username("doggu_love")
            .password("@password134")
            .owner(Owner.DOG_OWNER)
            .build();

        when(accountRepository.existsByEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> accountService.save(account))
            .isInstanceOf(AuthException.class)
            .hasMessageContainingAll(ErrorCode.DUPLICATED_EMAIL.getMessage());
    }

    @Test
    @DisplayName("중복된 사용자 이름을 가진 회원정보는 저장할 수 없다.")
    void saveWithDuplicateUsername() {
        String username = "doggu_love";
        Account account = Account.builder()
            .email("abcd1234@gmail.com")
            .username("doggu_love")
            .password("@password134")
            .owner(Owner.DOG_OWNER)
            .build();

        when(accountRepository.existsByUsername(username)).thenReturn(true);

        assertThatThrownBy(() -> accountService.save(account))
            .isInstanceOf(AuthException.class)
            .hasMessageContainingAll(ErrorCode.DUPLICATED_USERNAME.getMessage());
    }
}
