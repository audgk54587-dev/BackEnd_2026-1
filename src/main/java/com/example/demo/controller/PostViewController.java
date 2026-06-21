package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostViewController {
    private final ArticleService articleService;
    private final BoardService boardService;

    public PostViewController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String freeposts(@RequestParam("boardId") Long boardId, Model model) {

        String boardName = boardService.getBoardById(boardId).getName();

        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articleService.getArticles(boardId));

        return "posts";
    }
}