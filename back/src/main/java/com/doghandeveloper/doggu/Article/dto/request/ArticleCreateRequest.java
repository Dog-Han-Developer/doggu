package com.doghandeveloper.doggu.Article.dto.request;

import com.doghandeveloper.doggu.Account.domain.Account;
import com.doghandeveloper.doggu.Article.domain.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "게시글 등록 요청")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class ArticleCreateRequest {
    private String title;
    private String content;
    private String[] category;

    @Builder
    public ArticleCreateRequest(String title, String content, String[] category){
        this.title = title;
        this.content = content;
        this.category = category;
    }
    public Article to(Account account){
        return Article.builder()
                .account(account)
                .content(content)
                .build();
    }
}