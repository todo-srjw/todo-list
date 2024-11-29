package com.example.todolist.Board.service;

import com.example.todolist.Board.entity.Board;
import com.example.todolist.Board.entity.BoardDTO;
import java.util.List;

public interface BoardService {
    List<Board> getAllPost();

    BoardDTO getPostById(Long id);

    void createPost(BoardDTO planDto);

    BoardDTO updatePost(Long id, BoardDTO planDto);

    void deletePost(Long id);
}
