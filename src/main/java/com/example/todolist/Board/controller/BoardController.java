package com.example.todolist.Board.controller;

import com.example.todolist.Board.entity.Board;
import com.example.todolist.Board.entity.BoardDTO;
import com.example.todolist.Board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Board>> getBoardList() {
        log.info("BoardController.getBoardList start");
        return new ResponseEntity<>(boardService.getAllBoard(), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
        log.info("BoardController.getBoardById id : {}", id);
        BoardDTO boardDto = boardService.getBoardById(id);
        return boardDto != null ? ResponseEntity.ok(boardDto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/createBoard")
    public ResponseEntity<String> createBoard(@RequestBody Board board) {
        log.info("BoardController.createBoard board : {}", board);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("인증 정보가 유효하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 정보가 유효하지 않습니다.");
        }

        String username = authentication.getName();
        log.info("로그인된 사용자 EMAIL : {}", username);

        // 현재 로그인한 사용자의 이메일을 게시글에 설정
        board.setEmail(username);
        boardService.createBoard(board);

        return ResponseEntity.status(HttpStatus.CREATED).body("작성 완료");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        log.info("BoardController.updateBoard id : {}, boardDTO : {}" , id, boardDTO.toString());
        BoardDTO updatedBoard = boardService.updateBoard(id, boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        log.info("BoardController.deleteBoard id : {}" , id);
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }



}
