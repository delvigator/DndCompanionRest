package com.example.dndCompanion.repository;

import com.example.dndCompanion.entity.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {
CharacterClass findByName(String name);
}
