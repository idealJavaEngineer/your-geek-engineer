package com.example.yourgeekengineer.repositories;


import com.example.yourgeekengineer.entities.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
