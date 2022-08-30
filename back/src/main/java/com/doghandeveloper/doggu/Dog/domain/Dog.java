package com.doghandeveloper.doggu.Dog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private Account accountId;

    private String registerNumber;
    private String name;
    private String kind;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private float weight;
    private LocalDateTime birth;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public Dog(String registerNumber, String name, String kind, Sex sex, float weight, LocalDateTime birth, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.registerNumber = registerNumber;
        this.name = name;
        this.kind = kind;
        this.sex = sex;
        this.weight = weight;
        this.birth = birth;
        this.createdDate = createdDate;
        this.updatedDate = updateDate;
    }

}

