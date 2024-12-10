package com.example.todolist.notification.service;

import com.example.todolist.notification.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotification();
    void createNotification(Long taskId, String email);
}
