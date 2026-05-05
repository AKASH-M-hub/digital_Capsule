package com.example.demo.service;

import com.example.demo.entity.Capsule;
import com.example.demo.enums.CapsuleStatus;
import com.example.demo.repository.CapsuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
public class SchedulerService {

    @Autowired
    private CapsuleRepository capsuleRepo;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 60000) // every 1 min
    public void unlockCapsules() {

        List<Capsule> capsules =
                capsuleRepo.findByStatusAndUnlockCondition_UnlockDateTimeBefore(
                        CapsuleStatus.LOCKED,
                        LocalDateTime.now()
                );

        for (Capsule capsule : capsules) {

            capsule.setStatus(CapsuleStatus.UNLOCKED);
            capsuleRepo.save(capsule);

            // Notify owner
            notificationService.sendNotification(
                    capsule.getOwner(),
                    "Your capsule '" + capsule.getTitle() + "' is now unlocked!"
            );
        }
    }
}