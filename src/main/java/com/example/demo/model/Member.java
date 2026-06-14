package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Member {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member() {
        this.id = 0L;
        this.name = "";
        this.email = "";
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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