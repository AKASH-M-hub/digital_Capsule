package com.example.demo.repository;

import com.example.demo.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    List<Recipient> findByCapsuleCapsuleId(Long capsuleId);
}