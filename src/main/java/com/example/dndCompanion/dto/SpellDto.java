package com.example.dndCompanion.dto;

import lombok.*;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SpellDto {
    private String name;
    private String description;
    private String distance;
    private List<String> classes;
    private String school;
    private int level;
    private boolean isRitual;
    private Map<String,Boolean> spellComponents;
    private String timeApplication;
    private String timeAction;
}