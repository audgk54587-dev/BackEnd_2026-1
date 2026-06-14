package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Board {
    private Long id;

    @NotBlank
    private String name;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board() {
        this.id = 0L;
        this.name = "";
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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