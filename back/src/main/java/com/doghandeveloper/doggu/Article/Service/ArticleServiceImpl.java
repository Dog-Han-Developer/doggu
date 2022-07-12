package com.doghandeveloper.doggu.Article.Service;

import com.doghandeveloper.doggu.Article.domain.Article;
import com.doghandeveloper.doggu.Article.repository.ArticleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public void saveArticle(Article article){
        Article savedArticle = articleRepository.save(article);
    }
}