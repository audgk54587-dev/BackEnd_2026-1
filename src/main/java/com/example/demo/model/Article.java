package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Article {
    //변수 선언
    private Long id;
    //@NotNull
    @JsonProperty("author_id")
    private Long authorId;
    @NotNull
    @JsonProperty("board_id")
    @JsonAlias("boardId")
    private Long boardId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    //기본 생성자 -> 저장 공간 만들기
    public Article() {
        this.id = 0L;
        this.authorId = null;
        this.boardId = null;
        this.title = "";
        this.content = "";
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    //매개변수가 있는 생성자 -> 값 대입
    public Article(Long id, Long authorId, Long boardId, String title, String content) {
        this.id = id;
        this.authorId = authorId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}