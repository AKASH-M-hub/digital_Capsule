package com.example.demo.controller;

import com.example.demo.entity.Recipient;
import com.example.demo.entity.User;
import com.example.demo.service.RecipientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capsules")
public class RecipientController {

    @Autowired
    private RecipientService recipientService;

    @PostMapping("/{id}/recipients")
    public Recipient addRecipient(HttpServletRequest request,
                                  @PathVariable Long id,
                                  @RequestBody AddRecipientRequest addRecipientRequest) {

        User user = (User) request.getAttribute("user");
        return recipientService.addRecipient(
            user.getUserId(),
            id,
            addRecipientRequest.getEmail()
        );
    }

    @GetMapping("/{id}/recipients")
    public List<Recipient> getRecipients(HttpServletRequest request,
                                         @PathVariable Long id) {

        User user = (User) request.getAttribute("user");
        return recipientService.getRecipients(user.getUserId(), id);
    }

    @lombok.Data
    private static class AddRecipientRequest {
        private String email;
    }
}
