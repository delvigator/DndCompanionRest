package com.example.dndCompanion.repository;

import com.example.dndCompanion.entity.CharacterRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRaceRepository extends JpaRepository<CharacterRace, Long> {
    CharacterRace findByName(String name);
}
