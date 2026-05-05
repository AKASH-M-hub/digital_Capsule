package com.example.timecapsule.entity;
import com.example.timecapsule.enums.UnlockType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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