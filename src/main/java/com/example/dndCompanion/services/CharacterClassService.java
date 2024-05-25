package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.CharacterClassDto;
import com.example.dndCompanion.dto.ClassSkillDto;
import com.example.dndCompanion.dto.ClassSkillsPerLevelDto;
import com.example.dndCompanion.dto.ClassSkillsTextDto;
import com.example.dndCompanion.entity.*;
import com.example.dndCompanion.repository.CharacterClassRepository;
import com.example.dndCompanion.repository.SubRaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CharacterClassService {
    private final CharacterClassRepository classRepository;


    public CharacterClass change(CharacterClass characterClass) {
       return classRepository.save(characterClass);
    }

    public void delete(Long id) {
        classRepository.deleteById(id);
    }

    public List<CharacterClass> getAll() {
        return classRepository.findAll();
    }

    public CharacterClass create(CharacterClassDto dto) {
        return classRepository.save(getFromDto(dto));

    }

    CharacterClass getFromDto(CharacterClassDto dto) {
        List<ClassSkillsTextDto> classSkillsTextDto = dto.getClassSkillsText();
        List<ClassSkillsText> classSkillsText = new ArrayList<>();
        for (ClassSkillsTextDto i : classSkillsTextDto) {
            classSkillsText.add(ClassSkillsText.builder()
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
                .classInfo(ClassInfo.builder()
                        .description(dto.getClassInfo().getDescription())
                        .hitDice(dto.getClassInfo().getHitDice())
                        .saves(dto.getClassInfo().getSaves())
                        .simpleSkills(dto.getClassInfo().getSimpleSkills())
                        .startHealth(dto.getClassInfo().getStartHealth())
                        .numberOfSimpleSkills(dto.getClassInfo().getNumberOfSimpleSkills())
                        .build())
                .classSkillsPerLevel(classSkillsPerLevel)
                .classSkillsTexts(classSkillsText)
                .build();
    }
}
