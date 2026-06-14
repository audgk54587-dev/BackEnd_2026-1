package com.example.demo.service;

import com.example.demo.model.Board;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;

    public BoardService(BoardRepository boardRepository, ArticleRepository articleRepository) {
        this.boardRepository = boardRepository;
        this.articleRepository = articleRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, Board updatedBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));

        board.setName(updatedBoard.getName());
        board.setModifiedDate(LocalDateTime.now());

        return boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));

        if (articleRepository.existsByBoardId(id)) {
            throw new IllegalArgumentException("게시물이 존재하는 게시판은 삭제할 수 없습니다.");
        }

        boardRepository.deleteById(id);
    }
}