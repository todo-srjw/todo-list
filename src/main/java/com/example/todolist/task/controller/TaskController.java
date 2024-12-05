package com.example.todolist.task.controller;

import com.example.todolist.board.entity.Board;
import com.example.todolist.task.entity.Task;
import com.example.todolist.task.entity.TaskDTO;
import com.example.todolist.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo/task")
public class TaskController {
    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> getTodos() {
        log.info("TaskController.getTaskList start");
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }

    @PostMapping("/createTask")
    public ResponseEntity<String> createBoard(@RequestBody Task task) {
        log.info("TaskController.createTask task : {}", task);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("인증 정보가 유효하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 정보가 유효하지 않습니다.");
        }

        String username = authentication.getName();
        log.info("로그인된 사용자 EMAIL : {}", username);

        // 현재 로그인한 사용자의 이메일을 게시글에 설정
        task.setEmail(username);
        taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body("작성 완료");
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        log.info("TaskController.updateTask id : {}, boardDTO : {}" , id, taskDTO.toString());
        TaskDTO updateTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("TaskController.deleteTask id : {}", id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
