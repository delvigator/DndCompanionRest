package com.example.dndCompanion.repository;

import com.example.dndCompanion.entity.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<GameCharacter,Long> {

}
