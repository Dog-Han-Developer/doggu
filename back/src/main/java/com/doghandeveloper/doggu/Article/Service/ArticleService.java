package com.doghandeveloper.doggu.Article.Service;

import com.doghandeveloper.doggu.Article.domain.Article;
import com.doghandeveloper.doggu.Article.dto.request.ArticleCreateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface ArticleService {
    public void saveArticle(Article article);
}
