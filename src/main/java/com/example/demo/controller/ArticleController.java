package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/posts")
    public List<Article> getArticles() {
        return articleService.getAllArticles();
        //DB에서 꺼내온 List<Article>을 그대로 반환
        //스프링 부트가 중간에서 알아서 자바 객체를 JSON 문자열로 자동 번역
    }

    @GetMapping("/articles")
    public JsonNode getAllArticlesJson() {
        return articleService.getCustomJsonArticles();
        //서비스에서 ObjectMapper를 사용해 JsonNode을 반환
        //이미 백엔드 코드에서 완성해 둔 JSON 구조를 그대로 직송
    }

}
