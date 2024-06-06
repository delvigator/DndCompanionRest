package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.SpellDto;
import com.example.dndCompanion.entity.Spell;
import com.example.dndCompanion.repository.SpellRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpellService {
    @Autowired
  SpellRepository spellRepository;

 public List<Spell> getAll(){
     return spellRepository.findAll();
 }
 public Spell create(SpellDto dto){
     return spellRepository.save(Spell.builder()
                     .spellComponents(dto.getSpellComponents())
                     .classes(dto.getClasses())
                     .level(dto.getLevel())
                     .name(dto.getName())
                     .description(dto.getDescription())
                     .distance(dto.getDistance())
                     .school(dto.getSchool())
                     .timeAction(dto.getTimeAction())
                     .timeApplication(dto.getTimeApplication())
                     .isRitual(dto.isRitual())
             .build());
 }
 public void delete(Long id){
     spellRepository.deleteById(id);
 }
 public Spell change(Spell spell){
     return spellRepository.save(spell);
 }
}
