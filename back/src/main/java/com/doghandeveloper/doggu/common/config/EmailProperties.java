package com.doghandeveloper.doggu.common.config;

import lombok.Getter;

@Getter
public enum EmailProperties {
    SIGNUP_EMAIL_AUTH("[덕구] 회원가입 인증 번호 입니다.", "인증 번호: %s", 300000L);

    private final String subject;
    private final String textFormat;
    private final Long validTime;

    EmailProperties(String subject, String textFormat, Long validTime) {
        this.subject = subject;
        this.textFormat = textFormat;
        this.validTime = validTime;
    }
}
