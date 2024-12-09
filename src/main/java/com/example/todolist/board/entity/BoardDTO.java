package com.example.todolist.board.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id; //아이디
    private String name; //작성자
    private String title; //제목
    private String content; //내용
    private String email;

    private LocalDateTime createdDate; // 추가
    private LocalDateTime updatedDate; // 추가
}
