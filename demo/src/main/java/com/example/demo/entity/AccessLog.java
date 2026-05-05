package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    private LocalDateTime accessTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User accessedBy;

    @ManyToOne
    @JoinColumn(name = "capsule_id")
    private Capsule capsule;
}