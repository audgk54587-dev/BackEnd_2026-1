package com.example.demo.repository;

import com.example.demo.model.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {
    private final List<Board> boards;

    private long sequence = 4L;

    public BoardRepository() {
        boards = new ArrayList<>();
        boards.add(new Board(0L, "자유게시판"));
        boards.add(new Board(1L, "공지사항"));
        boards.add(new Board(2L, "Q&A게시판"));
        boards.add(new Board(3L, "취업정보"));
    }

    public List<Board> findAll() {
        return boards;
    }

    public Optional<Board> findById(Long id) {
        for (Board board : boards) {
            if (board.getId().equals(id)) {
                return Optional.of(board);
            }
        }
        return Optional.empty();
    }

    public Board save(Board board) {
        if (board.getId() == null || board.getId() == 0L) {
            board.setId(sequence++);
        } else {
            deleteById(board.getId());
        }
        boards.add(board);
        return board;
    }

    public void deleteById(Long id) {
        boards.removeIf(board -> board.getId().equals(id));
    }
}