package com.example.timecapsule.repository;

import com.timecapsule.entity.Notification;
import com.timecapsule.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserUserId(Long userId);

    List<Notification> findByStatus(NotificationStatus status);
}