package com.example.healthwebsite.repository;

import com.example.healthwebsite.entity.HealthMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthMemberRepository extends JpaRepository<HealthMember, String> {
}
