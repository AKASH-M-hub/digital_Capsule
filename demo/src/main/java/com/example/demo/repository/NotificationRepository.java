package com.example.demo.repository;

import com.example.demo.entity.Notification;
import com.example.demo.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserUserId(Long userId);

    List<Notification> findByStatus(NotificationStatus status);
}