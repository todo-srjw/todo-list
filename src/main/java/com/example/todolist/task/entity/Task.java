package com.example.todolist.task.entity;

import com.example.todolist.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String text;
    private String startDate;

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private String deleted = "N"; // 빌더에서도 기본값 설정
}
