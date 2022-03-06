package com.doghandeveloper.doggu.Article.repository;

import com.doghandeveloper.doggu.Article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}