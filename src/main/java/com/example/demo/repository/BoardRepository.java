package com.example.demo.repository;

import com.example.demo.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // findAll, findById, save, deleteById 모두 JpaRepository 기본 제공
}