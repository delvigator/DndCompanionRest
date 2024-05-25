package com.example.dndCompanion.dto;

import lombok.*;

import java.util.List;
import java.util.Map;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDto {
    private String name;
    private String description;
    private List<PeculiarityDto> peculiarities;
    private Map<String, Integer> bonuses;
}
