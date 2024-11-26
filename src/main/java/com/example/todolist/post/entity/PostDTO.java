package com.example.todolist.post.entity;

import com.example.todolist.common.BaseEntity;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO extends BaseEntity {
    private Long id; //아이디
    private String author; //작성자
    private String title; //제목
    private String content; //내용
}
