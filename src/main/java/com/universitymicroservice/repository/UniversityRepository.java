package com.universitymicroservice.repository;

import com.universitymicroservice.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
    boolean existsByCode(String code);
}