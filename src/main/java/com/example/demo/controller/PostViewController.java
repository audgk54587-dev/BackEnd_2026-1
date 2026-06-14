package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostViewController {
    private final ArticleService articleService;

    public PostViewController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String freeposts(Model model) {
        model.addAttribute("posts", articleService.getArticles(null));

        return "free";
        //그려낼 HTML 화면 파일 이름 지정
    }
}