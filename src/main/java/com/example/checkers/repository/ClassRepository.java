package com.example.checkers.repository;

import com.example.checkers.models.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<CharacterClass, Long> {
}
