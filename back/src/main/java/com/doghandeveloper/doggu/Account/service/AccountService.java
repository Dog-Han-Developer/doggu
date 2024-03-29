package com.doghandeveloper.doggu.Account.service;

public interface AccountService {
    void sendEmail(String email);

    void verifyAuthenticationCode(String authenticationCode, String code);

    void verifyDuplicateUsername(String userName);
}
