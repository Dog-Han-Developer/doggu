package com.doghandeveloper.doggu.Account.service;

import com.doghandeveloper.doggu.Account.domain.Account;

public interface AccountService {
    void sendEmail(String email);

    void verifyAuthenticationCode(String authenticationCode, String code);

    void verifyDuplicateUsername(String userName);

    void save(Account account);
}
