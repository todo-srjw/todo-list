package com.example.todolist.post.service;

import com.example.todolist.post.entity.PostDTO;
import com.example.todolist.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostServiceTest {

    private PostService postService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);  // Repository Mocking
        postService = new PostServiceImpl(postRepository);  // Mock Repository 주입
    }

    @Test
    void testGetAllPosts() {

    }
}