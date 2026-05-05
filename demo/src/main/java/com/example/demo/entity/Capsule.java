package com.example.demo.entity;

import com.example.demo.enums.CapsuleStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Capsule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long capsuleId;

    private String title;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String encryptedContent;

    @Enumerated(EnumType.STRING)
    private CapsuleStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToOne(mappedBy = "capsule", cascade = CascadeType.ALL)
    private UnlockCondition unlockCondition;

    @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
    private List<Recipient> recipients;
}