package com.example.demo.repository;

import com.example.demo.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDao {
    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Article> articleRowMapper = (rs, rowNum) -> {
        Article article = new Article();
        article.setId(rs.getLong("id"));
        article.setAuthorId(rs.getLong("author_id"));
        article.setBoardId(rs.getLong("board_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));

        if (rs.getTimestamp("created_date") != null) {
            article.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        }
        if (rs.getTimestamp("modified_date") != null) {
            article.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
        }
        return article;
    };

    public List<Article> findAll() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, articleRowMapper);
    }

    public List<Article> findByBoardId(Long boardId) {
        String sql = "SELECT * FROM article WHERE board_id = ?";
        return jdbcTemplate.query(sql, articleRowMapper, boardId);
    }

    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper, id);
        return result.stream().findFirst();
    }

    public Article save(Article article) {
        String sql = "INSERT INTO article (author_id, board_id, title, content, created_date, modified_date) " +
                "VALUES (?, ?, ?, ?, NOW(), NOW())";

        jdbcTemplate.update(sql,
                article.getAuthorId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent()
        );

        Long generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        article.setId(generatedId);

        article.setCreatedDate(LocalDateTime.now());
        article.setModifiedDate(LocalDateTime.now());

        return article;
    }

    public Article update(Article article) {
        String sql = "UPDATE article SET author_id = ?, board_id = ?, title = ?, content = ?, modified_date = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                article.getAuthorId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent(),
                Timestamp.valueOf(article.getModifiedDate()),
                article.getId()
        );

        return article;
    }

    public void delete(Article article) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, article.getId());
    }
}