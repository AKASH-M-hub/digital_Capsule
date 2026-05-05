package com.example.demo.controller;

import com.example.demo.entity.AccessLog;
import com.example.demo.entity.User;
import com.example.demo.service.AccessLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class AccessLogController {

    @Autowired
    private AccessLogService accessLogService;

    @GetMapping("/capsule/{id}")
    public List<AccessLog> getLogsByCapsule(HttpServletRequest request,
                                            @PathVariable Long id) {

        User user = (User) request.getAttribute("user");
        return accessLogService.getLogsByCapsule(user.getUserId(), id);
    }

    @GetMapping("/user/{userId}")
    public List<AccessLog> getLogsByUser(HttpServletRequest request,
                                         @PathVariable Long userId) {

        User user = (User) request.getAttribute("user");
        return accessLogService.getLogsByUser(user.getUserId(), userId);
    }
}
