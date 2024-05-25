package com.example.dndCompanion.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfoDto {

    private String description;
    private int hitDice;
    private int startHealth;
    private List<String> saves;
    private List<String> simpleSkills;
    private int numberOfSimpleSkills;

}
