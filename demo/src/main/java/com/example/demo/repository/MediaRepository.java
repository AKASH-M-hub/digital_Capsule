package com.example.demo.repository;

import com.example.demo.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByCapsuleCapsuleId(Long capsuleId);
}