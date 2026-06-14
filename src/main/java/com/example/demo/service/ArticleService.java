package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public ArticleService(ArticleRepository articleRepository,
                          MemberRepository memberRepository,
                          BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public List<Article> getArticles(Long boardId) {
        if (boardId == null) {
            return articleRepository.findAll();
        }
        return articleRepository.findByBoardId(boardId);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));
    }

    public Article createArticle(Article article) {
        memberRepository.findById(article.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        boardRepository.findById(article.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다."));

        memberRepository.findById(updatedArticle.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        boardRepository.findById(updatedArticle.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

        article.setAuthorId(updatedArticle.getAuthorId());
        article.setBoardId(updatedArticle.getBoardId());
        article.setTitle(updatedArticle.getTitle());
        article.setContent(updatedArticle.getContent());
        article.setModifiedDate(LocalDateTime.now()); // 수정일시 갱신

        return article;
    }

    public ArrayNode getCustomJsonArticles() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'");
        List<Article> articles = articleRepository.findAll();

        for (Article article : articles) {
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("title", article.getTitle());
            jsonObject.put("author", "회원" + article.getAuthorId());
            jsonObject.put("date", article.getCreatedDate().format(formatter));
            jsonObject.put("content", article.getContent());
            arrayNode.add(jsonObject);
        }
        return arrayNode;
    }
}