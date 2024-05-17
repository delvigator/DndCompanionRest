package com.example.checkers.models.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterClassDto {

    private String name;
    private int level;
    private ClassInfoDto classInfo;
    private List<ClassSkillsPerLevelDto> classSkillsPerLevel;
    private List<ClassSkillsTextDto> classSkillsText;

    // getters and setters
}
