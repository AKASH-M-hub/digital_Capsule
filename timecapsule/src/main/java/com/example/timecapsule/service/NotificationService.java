package com.example.timecapsule.service;

import com.example.timecapsule.entity.Notification;
import com.example.timecapsule.entity.User;
import com.example.timecapsule.enums.NotificationStatus;
import com.example.timecapsule.enums.NotificationType;
import com.example.timecapsule.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepo;

    public void sendNotification(User user, String message) {

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setType(NotificationType.IN_APP);
        notification.setStatus(NotificationStatus.SENT);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepo.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepo.findByUserUserId(userId);
    }
}