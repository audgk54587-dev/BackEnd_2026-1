package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostViewController {
    private ArticleService articleService;

    public PostViewController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String freeposts(Model model) {
        model.addAttribute("posts", articleService.getAllArticles());
        //articleService.getAllArticles(): DB에서 게시글 리스트를 가져옴
        //.addAttribute("posts", ...): 가져온 게시글 목록에 "posts"라는 이름표를 붙여 바구니(model)에 넣음
        return "free";
        //그려낼 HTML 화면 파일 이름 지정
    }
}
