package com.doghandeveloper.doggu.Account.domain;

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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    private Owner owner;

    @Lob
    private String introduction;

    //TODO: 범석오빠가 커밋하면 abstract class로 변경
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Builder
    public Account(String email, String username, String password, Owner owner, String introduction) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.owner = owner;
        this.introduction = introduction;
    }
}
