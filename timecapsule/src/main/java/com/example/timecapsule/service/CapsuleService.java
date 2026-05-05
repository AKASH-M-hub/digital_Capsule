package com.example.timecapsule.service;

import com.example.timecapsule.entity.*;
import com.example.timecapsule.enums.CapsuleStatus;
import com.example.timecapsule.enums.UnlockType;
import com.example.timecapsule.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CapsuleService {

    @Autowired
    private CapsuleRepository capsuleRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EncryptionService encryptionService;

    public Capsule createCapsule(Long userId,
                                 String title,
                                 String description,
                                 String content,
                                 LocalDateTime unlockTime) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = new Capsule();
        capsule.setTitle(title);
        capsule.setDescription(description);
        capsule.setEncryptedContent(encryptionService.encrypt(content));
        capsule.setStatus(CapsuleStatus.LOCKED);
        capsule.setCreatedAt(LocalDateTime.now());
        capsule.setOwner(user);

        UnlockCondition condition = new UnlockCondition();
        condition.setUnlockType(UnlockType.TIME);
        condition.setUnlockDateTime(unlockTime);
        condition.setCapsule(capsule);

        capsule.setUnlockCondition(condition);

        return capsuleRepo.save(capsule);
    }

    public List<Capsule> getUserCapsules(Long userId) {
        return capsuleRepo.findByOwnerUserId(userId);
    }

    public String getDecryptedContent(Long capsuleId) {

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        if (capsule.getStatus() != CapsuleStatus.UNLOCKED) {
            throw new RuntimeException("Capsule still locked");
        }

        return encryptionService.decrypt(capsule.getEncryptedContent());
    }
}