package com.example.demo.service;

import com.example.demo.entity.Capsule;
import com.example.demo.entity.Recipient;
import com.example.demo.entity.User;
import com.example.demo.repository.CapsuleRepository;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {

    @Autowired
    private CapsuleRepository capsuleRepo;

    @Autowired
    private RecipientRepository recipientRepo;

    @Autowired
    private UserRepository userRepo;

    public Recipient addRecipient(Long userId, Long capsuleId, String email) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        if (capsule.getOwner() == null || !capsule.getOwner().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Only owner can add recipients");
        }

        Recipient recipient = new Recipient();
        recipient.setEmail(email);
        recipient.setCapsule(capsule);

        return recipientRepo.save(recipient);
    }

    public List<Recipient> getRecipients(Long userId, Long capsuleId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        if (capsule.getOwner() == null || !capsule.getOwner().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Only owner can view recipients");
        }

        return recipientRepo.findByCapsuleCapsuleId(capsuleId);
    }
}
