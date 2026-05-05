package com.example.demo.repository;

import com.example.demo.entity.Capsule;
import com.example.demo.enums.CapsuleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CapsuleRepository extends JpaRepository<Capsule, Long> {

    List<Capsule> findByOwnerUserId(Long userId);

    List<Capsule> findByStatus(CapsuleStatus status);

    List<Capsule> findByStatusAndUnlockCondition_UnlockDateTimeBefore(
            CapsuleStatus status,
            LocalDateTime time
    );
}