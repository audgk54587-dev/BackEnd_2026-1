package com.example.demo.repository;

import com.example.demo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByBoardId(Long boardId);

    boolean existsByAuthorId(Long authorId);

    boolean existsByBoardId(Long boardId);
}