package com.doghandeveloper.doggu.Account.controller.docs;

import com.doghandeveloper.doggu.common.utils.ApiDocumentUtils;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class AccountDocumentation {
    public static RestDocumentationResultHandler sendAuthenticationEmail() {
        return document("accounts/email",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                ));
    }

    public static RestDocumentationResultHandler verifyAuthenticationCode() {
        return document("accounts/email/verify",
            ApiDocumentUtils.getDocumentRequest(),
            ApiDocumentUtils.getDocumentResponse(),
            requestFields(
                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                fieldWithPath("authenticationCode").type(JsonFieldType.STRING).description("인증번호")
            ));
    }
}
