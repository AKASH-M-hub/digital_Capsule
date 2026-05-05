package com.example.example.timecapsule.repository;

import com.example.timecapsule.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    List<AccessLog> findByCapsuleCapsuleId(Long capsuleId);

    List<AccessLog> findByAccessedByUserId(Long userId);
}