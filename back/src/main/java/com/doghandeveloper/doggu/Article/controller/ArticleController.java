package com.doghandeveloper.doggu.Article.controller;

import com.doghandeveloper.doggu.Account.domain.Account;
import com.doghandeveloper.doggu.Article.Service.ArticleService;
import com.doghandeveloper.doggu.Article.Service.ArticleServiceImpl;
import com.doghandeveloper.doggu.Article.domain.Article;
import com.doghandeveloper.doggu.Article.dto.request.ArticleCreateRequest;
import com.doghandeveloper.doggu.common.exception.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@Tag(name = "article", description = "게시글 관련 컨트롤러")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    @Operation(summary = "게시글 등록", description = "게시글을 등록합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "게시글 등록 성공"),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 등록 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    // todo : account Annotation, CurrentAccount 의미
    public ResponseEntity<Void> saveArticle(@Valid @RequestBody ArticleCreateRequest articleCreateRequest, @Parameter(hidden=true) Account account) {
        Article article = articleCreateRequest.to(account);
        articleService.saveArticle(article);
        log.info("게시글 등록 성공: {}", articleCreateRequest.getTitle());
        return ResponseEntity.ok().build();
    }
}
