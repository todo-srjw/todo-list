package com.example.todolist.notification.service;

import com.example.todolist.notification.entity.Notification;
import com.example.todolist.notification.repositrory.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public void createNotification(Long taskId, String email) {
            Notification notification = new Notification();
            notification.setTodoId(taskId);
            notification.setEmail(email);
            notification.setMessage("새로운 작업이 추가되었습니다.");
            notification.setType("NEW_TASK");

            notificationRepository.save(notification);
    }
}
