package com.example.dndCompanion.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubRaceDto {
    private String name;
    private int numberFeatures;
    private String description;
    private List<SubRaceDto> subRace;
    private List<PeculiarityDto> peculiarities;
    private List<SkillMasteryDto> skillMastery;
    private Map<String, Integer> skillBoost;
}