package com.example.todolist.notification.entity;

import com.example.todolist.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "notification")

public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private Long todoId;
    private String message;
    private String type;

    @Column(name = "isRead", nullable = false)
    @Builder.Default
    private String isRead = "N";
}
