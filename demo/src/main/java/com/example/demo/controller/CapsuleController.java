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
                                 @RequestParam String title,
                                 @RequestParam String description,
                                 @RequestParam String content,
                                 @RequestParam String unlockTime) {

        User user = (User) request.getAttribute("user");

        return capsuleService.createCapsule(
                user.getUserId(),
                title,
                description,
                content,
                LocalDateTime.parse(unlockTime)
        );
    }

    @GetMapping("/my")
    public List<Capsule> getMyCapsules(HttpServletRequest request) {

        User user = (User) request.getAttribute("user");
        return capsuleService.getUserCapsules(user.getUserId());
    }

    @GetMapping("/{id}/content")
    public String getContent(@PathVariable Long id) {
        return capsuleService.getDecryptedContent(id);
    }
}