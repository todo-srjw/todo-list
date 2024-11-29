package com.example.todolist.post.service;

import com.example.todolist.Board.entity.Board;
import com.example.todolist.Board.repository.BoardRepository;
import com.example.todolist.Board.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest  // Spring Boot 환경에서 테스트를 실행
class BoardServiceTest {

    @Autowired
    private BoardService boardService;  // 실제 BoardService를 주입

    @Autowired
    private BoardRepository boardRepository;  // 실제 BoardRepository를 주입

    // 테스트 데이터를 삽입하는 메서드
    private void insertTestData() {
        Board board = Board.builder()
                .author("김소련")
                .title("Vue.js로 게시판 만들기")
                .content("이것은 Vue.js와 Spring Boot를 사용하여 게시판을 구현하는 방법에 대한 글입니다.")
                .build();

        boardRepository.save(board);  // 데이터를 DB에 저장
    }

    @BeforeEach
    void setUp() {
        // 테스트마다 데이터를 삽입
        insertTestData();
    }

    @Test
    void testGetAllBoards() {
        // 모든 게시글을 조회하는 테스트
        List<Board> boards = boardService.getAllPost();

        // 게시글이 하나 이상 있어야 함
        assertNotNull(boards);
        assertTrue(boards.size() > 0);  // 하나 이상의 게시글이 있어야 한다는 조건
    }
}