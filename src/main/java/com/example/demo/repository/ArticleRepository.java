package com.example.demo.repository;

import com.example.demo.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;     //ArrayList를 꺼내 쓰기 위해
import java.util.List;          //List를 사용하기 위해

@Repository
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    private long sequence = 4L;

    public ArticleRepository() {
        articles.add(new Article(0L, 0L, 0L, "제목0", "내용0"));
        //우리가 설계했던 임시 저장소 변수(articles)에 실제 데이터를 담을 수 있는 가방(ArrayList)을 새로 만들어서 연결
        articles.add(new Article(0L, 0L, 0L, "제목0", " "));
        articles.add(new Article(1L, 1L, 1L, "제목1", "내용입니다!!"));
        articles.add(new Article(2L, 2L, 2L, "제목2", "내용입니다!!내용입니다!!"));
        articles.add(new Article(3L, 3L, 3L, "제목2", "내용입니다!!내용입니다!!내용입니다!!"));
    }

    public List<Article> findAll() {
    //findAll(): 저장된 데이터를 모두 찾음
        return articles;
    }

    public Article save(Article article) {
        article.setId(sequence++);
        articles.add(article);
        return article;
    }

    public List<Article> findByBoardId(Long boardId) {
        List<Article> filtered = new ArrayList<>();
        for (Article article : articles) {
            if (article.getBoardId().equals(boardId)) {
                filtered.add(article);
            }
        }
        return filtered;
    }

    public Optional<Article> findById(Long id) {
        for (Article article : articles) {
            if (article.getId().equals(id)) {
                return Optional.of(article);
            }
        }
        return Optional.empty();
    }

    public boolean existsByAuthorId(Long authorId) {
        for (Article article : articles) {
            if (article.getAuthorId().equals(authorId)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsByBoardId(Long boardId) {
        for (Article article : articles) {
            if (article.getBoardId().equals(boardId)) {
                return true;
            }
        }
        return false;
    }

    public void delete(Article article) {
        articles.remove(article);
    }
}