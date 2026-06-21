package com.example.demo.repository;

import com.example.demo.model.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;     //ArrayList를 꺼내 쓰기 위해
import java.util.List;          //List를 사용하기 위해

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public ArticleRepository() {
    }

    public List<Article> findAll() {
        return em.createQuery("SELECT a FROM Article a", Article.class)
                .getResultList();
    }

    public Article save(Article article) {
        em.persist(article);
        return article;
    }

    public List<Article> findByBoardId(Long boardId) {
        return em.createQuery("SELECT a FROM Article a WHERE a.boardId = :boardId", Article.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }

    public boolean existsByAuthorId(Long authorId) {
        Long count = em.createQuery("SELECT COUNT(a) FROM Article a WHERE a.authorId = :authorId", Long.class)
                .setParameter("authorId", authorId)
                .getSingleResult();
        return count > 0;
    }

    public boolean existsByBoardId(Long boardId) {
        Long count = em.createQuery("SELECT COUNT(a) FROM Article a WHERE a.boardId = :boardId", Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
        return count > 0;
    }

    public void delete(Article article) {
        Article target = em.find(Article.class, article.getId());
        if (target != null) {
            em.remove(target);
        }
    }
}