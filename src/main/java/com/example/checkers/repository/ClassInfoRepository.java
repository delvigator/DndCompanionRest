package com.example.checkers.repository;

import com.example.checkers.models.CharacterClass;
import com.example.checkers.models.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {
}
