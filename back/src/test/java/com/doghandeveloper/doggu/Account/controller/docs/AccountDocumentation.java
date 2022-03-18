package com.doghandeveloper.doggu.Account.controller.docs;

import com.doghandeveloper.doggu.common.utils.ApiDocumentUtils;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class AccountDocumentation {
    public static RestDocumentationResultHandler emailAuthentication() {
        return document("accounts/email",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                ));
    }
}
