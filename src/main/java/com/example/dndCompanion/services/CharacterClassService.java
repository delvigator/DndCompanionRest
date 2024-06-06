package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.*;
import com.example.dndCompanion.entity.*;
import com.example.dndCompanion.repository.CharacterClassRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class CharacterClassService {
    @Autowired
   CharacterClassRepository classRepository;


    public CharacterClass change(CharacterClass characterClass) {
       return classRepository.save(characterClass);
    }

    public void delete(Long id) {
        classRepository.deleteById(id);
    }

    public List<CharacterClass> getAll() {
        log.info("getAll");
        return classRepository.findAll();
    }

    public CharacterClass create(CharacterClassDto dto) {
        return classRepository.save(getFromDto(dto));

    }

    CharacterClass getFromDto(CharacterClassDto dto) {
        List<ClassSkillsTextDto> classSkillsTextDto = dto.getPeculiarity();
        List<Peculiarity> classSkillsText = new ArrayList<>();
        for (ClassSkillsTextDto i : classSkillsTextDto) {
            classSkillsText.add(Peculiarity.builder()
                    .title(i.getTitle())
                    .text(i.getText())
                    .build());
        }
        List<ClassSkillsPerLevelDto> classSkillsPerLevelDto = dto.getClassSkillsPerLevel();
        List<ClassSkillsPerLevel> classSkillsPerLevel = new ArrayList<>();
        for (ClassSkillsPerLevelDto i : classSkillsPerLevelDto) {
            List<ClassSkillDto> classSkillsDto = i.getClassSkills();
            List<ClassSkill> classSkills = new ArrayList<>();
            for (ClassSkillDto j : classSkillsDto) {
                classSkills.add(ClassSkill.builder()
                        .skillName(j.getSkillName())
                        .skillDescription(j.getSkillDescription())
                        .build());
            }
            classSkillsPerLevel.add(ClassSkillsPerLevel.builder()
                    .level(i.getLevel())
                    .masteryBonus(i.getMasteryBonus())
                    .spellSlots(i.getSpellSlots())
                    .numberKnownFocuses(i.getNumberKnownFocuses())
                    .numberKnownSpells(i.getNumberKnownSpells())
                    .classSkills(classSkills)
                    .build());
        }
        return CharacterClass.builder()
                .name(dto.getName())
                .level(dto.getLevel())
                        .description(dto.getDescription())
                        .hitDice(dto.getHitDice())
                        .saves(dto.getSaves())
                        .simpleSkills(dto.getSimpleSkills())
                        .startHealth(dto.getStartHealth())
                        .numberOfSimpleSkills(dto.getNumberOfSimpleSkills())
                .classSkillsPerLevel(classSkillsPerLevel)
                .peculiarity(classSkillsText)
                .build();
    }
}
