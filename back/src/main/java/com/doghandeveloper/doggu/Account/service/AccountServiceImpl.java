package com.doghandeveloper.doggu.Account.service;

import com.doghandeveloper.doggu.Account.repository.AccountRepository;
import com.doghandeveloper.doggu.common.config.EmailProperties;
import com.doghandeveloper.doggu.common.exception.AuthException;
import com.doghandeveloper.doggu.common.exception.dto.ErrorCode;
import com.doghandeveloper.doggu.common.utils.EmailSendUtil;
import com.doghandeveloper.doggu.common.utils.RedisUtil;
import java.security.InvalidParameterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final EmailSendUtil emailSendUtil;
    private final RedisUtil redisUtil;

    @Override
    public void sendEmail(String email) {
        if (accountRepository.existsByEmail(email)) {
            throw new AuthException(ErrorCode.DUPLICATED_EMAIL);
        }
        final String authCode = createCode();
        EmailProperties properties = EmailProperties.SIGNUP_EMAIL_AUTH;
        emailSendUtil.sendEmail(email, properties.getSubject(), String.format(properties.getTextFormat(), authCode));
        redisUtil.setDataExpire(email, authCode, properties.getValidTime());
    }

    private String createCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public void verifyAuthenticationCode(String email, String authenticationCode) {
        String savedAuthenticationCode = redisUtil.getData(email);
        if(!authenticationCode.equals(savedAuthenticationCode)){
            throw new InvalidParameterException();
        }
    }
}
