package com.example.demo.controller;

import com.example.demo.entity.Media;
import com.example.demo.entity.User;
import com.example.demo.service.MediaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/upload")
    public Media uploadMedia(HttpServletRequest request,
                             @RequestParam Long capsuleId,
                             @RequestParam String fileUrl,
                             @RequestParam String fileType) {

        User user = (User) request.getAttribute("user");
        return mediaService.addMedia(user.getUserId(), capsuleId, fileUrl, fileType);
    }

    @GetMapping("/{capsuleId}")
    public List<Media> getMedia(HttpServletRequest request, @PathVariable Long capsuleId) {

        User user = (User) request.getAttribute("user");
        return mediaService.getMedia(user.getUserId(), capsuleId);
    }
}
