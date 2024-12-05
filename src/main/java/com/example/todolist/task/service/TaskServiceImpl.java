package com.example.todolist.task.service;

import com.example.todolist.task.entity.Task;
import com.example.todolist.task.entity.TaskDTO;
import com.example.todolist.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTask() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        return taskRepository.findAllByDeleted("N", sort);
    }

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        task.setText(taskDTO.getText());
        task.setStartDate(taskDTO.getStartDate());
        taskRepository.save(task);
        return taskDTO;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("게시글을 찾을 수 없습니다.")));
        task.setDeleted("Y");
        taskRepository.save(task);
    }
}
