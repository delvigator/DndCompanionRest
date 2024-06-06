package com.example.dndCompanion.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterClassDto {

    private String name;
    private int level;
    private String description;
    private int hitDice;
    private int startHealth;
    private List<String> saves;
    private List<String> simpleSkills;
    private int numberOfSimpleSkills;
    private List<ClassSkillsPerLevelDto> classSkillsPerLevel;
    private List<ClassSkillsTextDto> peculiarity;

    // getters and setters
}
