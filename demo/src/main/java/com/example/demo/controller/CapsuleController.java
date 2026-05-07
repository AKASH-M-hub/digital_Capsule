package com.example.demo.controller;

import com.example.demo.entity.Capsule;
import com.example.demo.entity.User;
import com.example.demo.service.CapsuleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/capsules")
public class CapsuleController {

    @Autowired
    private CapsuleService capsuleService;

    @PostMapping("/create")
    public Capsule createCapsule(HttpServletRequest request,
                                 @RequestBody CreateCapsuleRequest createRequest) {

        User user = (User) request.getAttribute("user");

        return capsuleService.createCapsule(
                user.getUserId(),
            createRequest.getTitle(),
            createRequest.getDescription(),
            createRequest.getContent(),
            LocalDateTime.parse(createRequest.getUnlockTime())
        );
    }

    @GetMapping("/my")
    public List<Capsule> getMyCapsules(HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        return capsuleService.getUserCapsules(user.getUserId());
    }

    @GetMapping("/{id}")
    public Capsule getCapsuleById(HttpServletRequest request, @PathVariable Long id) {

        User user = (User) request.getAttribute("user");
        return capsuleService.getCapsule(user.getUserId(), id);
    }

    @GetMapping("/{id}/content")
    public String getContent(HttpServletRequest request, @PathVariable Long id) {

        User user = (User) request.getAttribute("user");
        return capsuleService.getDecryptedContent(id, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public String deleteCapsule(HttpServletRequest request, @PathVariable Long id) {

        User user = (User) request.getAttribute("user");
        capsuleService.deleteCapsule(user.getUserId(), id);
        return "Deleted Successfully";
    }

    @lombok.Data
    private static class CreateCapsuleRequest {
        private String title;
        private String description;
        private String content;
        private String unlockTime;
    }
}