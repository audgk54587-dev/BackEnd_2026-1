package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ArticleService {
    //변수 선언
    private ArticleRepository articleRepository;

    //생성자
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //비즈니스 메서드
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
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
