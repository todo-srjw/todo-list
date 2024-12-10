package com.example.todolist.notification.entity;


import com.example.todolist.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO extends BaseEntity {
    private Long id;
    private String email;
    private int todoId;
    private String message;
    private String type;
    private String isRead;
}
