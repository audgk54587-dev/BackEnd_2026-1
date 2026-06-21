package com.example.demo.repository;

import com.example.demo.model.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDao {

    @PersistenceContext
    private EntityManager em;


    public List<Article> findAll() {
        return em.createQuery("SELECT a FROM Article a", Article.class)
                .getResultList();
    }

    public List<Article> findByBoardId(Long boardId) {
        return em.createQuery("SELECT a FROM Article a WHERE a.boardId = :boardId", Article.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public Optional<Article> findById(Long id) {
        Article article = em.find(Article.class, id);
        return Optional.ofNullable(article);
    }

    public Article save(Article article) {
        article.setCreatedDate(LocalDateTime.now());
        article.setModifiedDate(LocalDateTime.now());
        em.persist(article);

        return article;
    }

    public Article update(Article article) {
        article.setModifiedDate(LocalDateTime.now());

        return em.merge(article);
    }

    public void delete(Article article) {
        Article target = em.find(Article.class, article.getId());
        if (target != null) {
            em.remove(target);
        }
    }
}