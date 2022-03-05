package com.doghandeveloper.doggu.Article.domain;

import com.doghandeveloper.doggu.Account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private String title;

    @Lob
    private String content;
    private Long likeCount;
    private String createdDate;
    private String updatedDate;
//    private String[] category;

    @Builder
    public Article(Account account, String content){
        this.account = account;
        this.content = content;
    }
}