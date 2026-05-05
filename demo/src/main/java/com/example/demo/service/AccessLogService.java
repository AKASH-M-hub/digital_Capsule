package com.example.demo.service;

import com.example.demo.entity.AccessLog;
import com.example.demo.entity.Capsule;
import com.example.demo.entity.User;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.CapsuleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccessLogService {

    @Autowired
    private AccessLogRepository accessLogRepo;

    @Autowired
    private CapsuleRepository capsuleRepo;

    @Autowired
    private UserRepository userRepo;

    public void logAccess(User user, Capsule capsule) {

        AccessLog log = new AccessLog();
        log.setAccessedBy(user);
        log.setCapsule(capsule);
        log.setAccessTime(LocalDateTime.now());

        accessLogRepo.save(log);
    }

    public List<AccessLog> getLogsByCapsule(Long userId, Long capsuleId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        if (capsule.getOwner() == null || !capsule.getOwner().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Only owner can view capsule logs");
        }

        return accessLogRepo.findByCapsuleCapsuleId(capsuleId);
    }

    public List<AccessLog> getLogsByUser(Long userId, Long targetUserId) {

        if (!userId.equals(targetUserId)) {
            throw new RuntimeException("Only user can view their logs");
        }

        return accessLogRepo.findByAccessedByUserId(targetUserId);
    }
}
