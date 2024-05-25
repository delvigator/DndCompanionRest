package com.example.dndCompanion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubRace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int numberFeatures;
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Peculiarity> peculiarities;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SkillMastery> skillMastery;

    @ElementCollection
    private Map<String, Integer> skillBoost;
}