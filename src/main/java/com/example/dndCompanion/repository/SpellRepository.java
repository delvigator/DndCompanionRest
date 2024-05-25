package com.example.dndCompanion.repository;

import com.example.dndCompanion.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepository extends JpaRepository<Spell,Long> {
    Spell findByName(String name);
}
