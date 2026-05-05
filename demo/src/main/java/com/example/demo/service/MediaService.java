package com.example.demo.service;

import com.example.demo.entity.Capsule;
import com.example.demo.entity.Media;
import com.example.demo.entity.User;
import com.example.demo.repository.CapsuleRepository;
import com.example.demo.repository.MediaRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    private CapsuleRepository capsuleRepo;

    @Autowired
    private MediaRepository mediaRepo;

    @Autowired
    private UserRepository userRepo;

    public Media addMedia(Long userId, Long capsuleId, String fileUrl, String fileType) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        if (capsule.getOwner() == null || !capsule.getOwner().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Only owner can add media");
        }

        Media media = new Media();
        media.setFileUrl(fileUrl);
        media.setFileType(fileType);
        media.setCapsule(capsule);

        return mediaRepo.save(media);
    }

    public List<Media> getMedia(Long userId, Long capsuleId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Capsule capsule = capsuleRepo.findById(capsuleId)
                .orElseThrow(() -> new RuntimeException("Capsule not found"));

        if (capsule.getOwner() == null || !capsule.getOwner().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Only owner can view media");
        }

        return mediaRepo.findByCapsuleCapsuleId(capsuleId);
    }
}
