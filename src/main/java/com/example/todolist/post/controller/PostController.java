package com.example.todolist.post.controller;

import com.example.todolist.post.entity.Post;
import com.example.todolist.post.entity.PostDTO;
import com.example.todolist.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        log.info("PostController.getAllPosts start");
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        log.info("PostController.getPostById id : {}", id);
        PostDTO postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto) {
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
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        log.info("PostController.updatePost id : {}, postDto : {}" , id, postDTO.toString());
        PostDTO updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
