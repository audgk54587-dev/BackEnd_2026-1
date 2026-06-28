package com.example.demo.service;

import com.example.demo.model.Board;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));
    }

    @Transactional
    public Board createBoard(Board board) {
        board.setCreatedDate(LocalDateTime.now());
        board.setModifiedDate(LocalDateTime.now());
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateBoard(Long id, Board updatedBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));

        board.setName(updatedBoard.getName());
        board.setModifiedDate(LocalDateTime.now());

        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));

        // cascade = CascadeType.ALL + orphanRemoval = true 설정으로
        // Board 삭제 시 Article도 자동 삭제되므로 existsByBoardId 체크 제거
        boardRepository.delete(board);
    }
}