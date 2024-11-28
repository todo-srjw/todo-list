package com.example.todolist.post.service;

import com.example.todolist.post.entity.Post;
import com.example.todolist.post.entity.PostDTO;
import java.util.List;

public interface PostService {
    List<Post> getAllPost();

    PostDTO getPostById(Long id);

    void createPost(PostDTO planDto);

    PostDTO updatePost(Long id, PostDTO planDto);

    void deletePost(Long id);
}
