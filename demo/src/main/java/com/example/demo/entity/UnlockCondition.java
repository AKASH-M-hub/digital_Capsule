package com.example.demo.entity;

import com.example.demo.enums.UnlockType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnlockCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conditionId;

    @Enumerated(EnumType.STRING)
    private UnlockType unlockType;

    private LocalDateTime unlockDateTime;

    private String eventKey;

    @OneToOne
    @JoinColumn(name = "capsule_id", unique = true)
    private Capsule capsule;
}