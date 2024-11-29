package com.example.todolist.Board.service;

import com.example.todolist.Board.entity.Board;
import com.example.todolist.Board.entity.BoardDTO;
import com.example.todolist.Board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository postRepository;

    public BoardServiceImpl(BoardRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<Board> getAllPost() {
        //실제 데이터를 가져오는 코드
        return postRepository.findAll();
    }

//데이터 없을때 테스트 코드용
//    @Override
//    public List<PostDTO> getAllPost() {
//        // 임시로 DTO 객체를 반환하는 예시
//        // TO-DO : 실제 데이터 가져오기 SELECT
//        PostDTO post1 = new PostDTO(1L, "author1", "title1", "content1", LocalDateTime.now());
//        PostDTO post2 = new PostDTO(2L, "author2", "title2", "content2", LocalDateTime.now());
//        return List.of(post1, post2);  // 임시로 2개의 데이터 반환
//    }

    @Override
    public BoardDTO getPostById(Long id) {
        // TO-DO
        // 1. 아이디값이 여기에 잘 가져오는지
        // 2. 조회
        return null;
    }

    @Override
    public void createPost(BoardDTO planDto) {

    }

    @Override
    public BoardDTO updatePost(Long id, BoardDTO planDto) {

        return planDto;
    }

    @Override
    public void deletePost(Long id) {

    }
}
