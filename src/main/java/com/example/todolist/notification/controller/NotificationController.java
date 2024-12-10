package com.example.todolist.notification.controller;

import com.example.todolist.notification.entity.Notification;
import com.example.todolist.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo/notifications")
public class NotificationController {
    final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Notification>> getNotifications(){
        log.info("NotificationController.getNotifications start");
        return new ResponseEntity<>(notificationService.getAllNotification(), HttpStatus.OK);
    }
}
