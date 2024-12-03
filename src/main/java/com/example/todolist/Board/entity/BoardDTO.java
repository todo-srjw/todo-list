package com.example.todolist.Board.entity;

import com.example.todolist.common.BaseEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO extends BaseEntity {
    private Long id; //아이디
    private String author; //작성자
    private String title; //제목
    private String content; //내용
}
