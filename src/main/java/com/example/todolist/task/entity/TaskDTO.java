package com.example.todolist.task.entity;


import com.example.todolist.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO extends BaseEntity {
    private Long id;
    private String email;
    private String text;
    private String startDate;
    private String deleted;
}
