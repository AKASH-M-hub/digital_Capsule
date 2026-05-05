package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.enums.CapsuleStatus;
import com.example.demo.enums.UnlockType;
import com.example.demo.repository.*;
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

    @Autowired
    private RecipientRepository recipientRepo;

    @Autowired
    private AccessLogService accessLogService;

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

    public Capsule getCapsule(Long userId, Long capsuleId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        assertCanAccess(user, capsule);
        return capsule;
    }

    public void deleteCapsule(Long userId, Long capsuleId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        if (!isOwner(user, capsule)) {
            throw new RuntimeException("Only owner can delete capsule");
        }

        capsuleRepo.delete(capsule);
    }

    public String getDecryptedContent(Long capsuleId, Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        assertCanAccess(user, capsule);

        if (capsule.getStatus() != CapsuleStatus.UNLOCKED) {
            throw new RuntimeException("Capsule still locked");
        }

        accessLogService.logAccess(user, capsule);
        return encryptionService.decrypt(capsule.getEncryptedContent());
    }

    private void assertCanAccess(User user, Capsule capsule) {

        if (isOwner(user, capsule)) {
            return;
        }

        boolean isRecipient = recipientRepo
                .existsByCapsuleCapsuleIdAndEmail(capsule.getCapsuleId(), user.getEmail());

        if (!isRecipient) {
            throw new RuntimeException("Access denied");
        }
    }

    private boolean isOwner(User user, Capsule capsule) {
        return capsule.getOwner() != null
                && capsule.getOwner().getUserId().equals(user.getUserId());
    }
}