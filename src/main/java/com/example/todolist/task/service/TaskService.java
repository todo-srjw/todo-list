package com.example.todolist.task.service;

import com.example.todolist.task.entity.Task;
import com.example.todolist.task.entity.TaskDTO;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();

    //getTaskById

    void createTask(Task task);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);
}
