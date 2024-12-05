package com.example.todolist.task.service;

import com.example.todolist.board.entity.Board;
import com.example.todolist.task.entity.Task;
import com.example.todolist.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional
@SpringBootTest
class TaskServiceTest {
    @Autowired
    private TaskService taskService; // 스프링 컨텍스트에서 관리하는 빈 주입

    @Autowired
    private TaskRepository taskRepository; // 테스트를 위해 Repository 주입

    private void insertTestData() {
        // 테스트 데이터 삽입
        Task task = Task.builder()
                .text("세차 하기")
                .email("test1@gmail.com")
                .startDate("2024-12-06") // 시작일 설정
                .build();

        taskRepository.save(task);
    }

    @BeforeEach
    void setUp() {
        // 테스트마다 데이터를 삽입
        insertTestData();
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = taskService.getAllTask();

        // tasks 리스트의 상태 출력
        System.out.println("tasks size: " + tasks.size());
        tasks.forEach(task -> System.out.println("Task: " + task.getText()));

        // 리스트가 null이 아니고, 하나 이상의 항목이 있어야 한다
        assertNotNull(tasks, "게시글 목록이 null이 아닙니다.");
        assertTrue(tasks.size() > 0, "게시글 목록에 하나 이상의 게시글이 포함되어야 합니다.");
    }
}
