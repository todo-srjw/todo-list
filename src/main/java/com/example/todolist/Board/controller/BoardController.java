package com.example.todolist.Board.controller;

import com.example.todolist.Board.entity.Board;
import com.example.todolist.Board.entity.BoardDTO;
import com.example.todolist.Board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo")
//@CrossOrigin(origins = "http://localhost:8081")  // Vue 앱이 실행되는 포트(8081)에 대해 허용
public class BoardController {

    @Autowired
    private BoardService postService;

    @GetMapping("/board")
    public ResponseEntity<List<Board>> getAllPosts() {
        log.info("PostController.getAllPosts start");
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getPostById(@PathVariable Long id) {
        log.info("PostController.getPostById id : {}", id);
        BoardDTO postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    public ResponseEntity<BoardDTO> createPost(@RequestBody BoardDTO postDto) {
        log.info("PostController.createPost postDto : {}", postDto);
        postService.createPost(postDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
//        logger.info("PostController.updatePost id : {}, postDto : {}" , id, postDTO.toString());
//        PostDTO updatedPost = postService.updatePost(id, postDTO);
//        return ResponseEntity.ok(updatedPost);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDTO> updatePost(@PathVariable Long id, @RequestBody BoardDTO postDTO) {
        log.info("PostController.updatePost id : {}, postDto : {}" , id, postDTO.toString());
        BoardDTO updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }



}
