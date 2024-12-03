package com.example.todolist.Board.service;

import com.example.todolist.Board.entity.Board;
import com.example.todolist.Board.entity.BoardDTO;
import java.util.List;

public interface BoardService {
    List<Board> getAllBoard();

    BoardDTO getBoardById(Long id);

    void createBoard(Board board);

    BoardDTO updateBoard(Long id, BoardDTO planDto);

    void deleteBoard(Long id);
}
