package com.example.dndCompanion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classSkillsPerLevel")
public class ClassSkillsPerLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer level;
    private Integer numberKnownFocuses;
    private Integer masteryBonus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "class_skills_per_level_id")
    private List<ClassSkill> classSkills;

    private Integer numberKnownSpells;

    @ElementCollection
    @MapKeyColumn(name = "level")
    private Map<Integer, Integer> spellSlots;


}
