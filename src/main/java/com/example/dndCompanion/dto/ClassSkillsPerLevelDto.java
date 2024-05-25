package com.example.dndCompanion.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassSkillsPerLevelDto {

    private int level;
    private int numberKnownFocuses;
    private int masteryBonus;
    private List<ClassSkillDto> classSkills;
    private int numberKnownSpells;
    private Map<Integer, Integer> spellSlots;

    // getters and setters
}