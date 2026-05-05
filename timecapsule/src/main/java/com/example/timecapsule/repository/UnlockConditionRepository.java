package com.exampletimecapsule.repository;

import com.example.timecapsule.entity.UnlockCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnlockConditionRepository extends JpaRepository<UnlockCondition, Long> {
}