package com.doghandeveloper.doggu.Article.domain;

import com.doghandeveloper.doggu.Account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;
    private long account_id;
    private String title;
    @Lob
    private String content;
    private Long likeCount;
//    private String[] category;

    @Builder
    public Article(long account, String title, String content){
        this.account_id = account;
        this.title = title;
        this.content = content;
    }
}