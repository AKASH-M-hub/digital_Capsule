package com.example.demo.repository;

import com.example.demo.entity.UnlockCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnlockConditionRepository extends JpaRepository<UnlockCondition, Long> {
}