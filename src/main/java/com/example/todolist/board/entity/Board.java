package com.example.todolist.board.entity;

import com.example.todolist.common.BaseEntity;
import lombok.*;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mno; //유저 아이디
    private String name;
    private String title;
    private String content;
    private String email;

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private String deleted = "N"; // 빌더에서도 기본값 설정
}