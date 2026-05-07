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
                             @RequestBody UploadMediaRequest uploadRequest) {

        User user = (User) request.getAttribute("user");
        return mediaService.addMedia(
            user.getUserId(),
            uploadRequest.getCapsuleId(),
            uploadRequest.getFileUrl(),
            uploadRequest.getFileType()
        );
    }

    @GetMapping("/{capsuleId}")
    public List<Media> getMedia(HttpServletRequest request, @PathVariable Long capsuleId) {

        User user = (User) request.getAttribute("user");
        return mediaService.getMedia(user.getUserId(), capsuleId);
    }

    @lombok.Data
    private static class UploadMediaRequest {
        private Long capsuleId;
        private String fileUrl;
        private String fileType;
    }
}
