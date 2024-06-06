package com.example.dndCompanion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "characterClass")
public class CharacterClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer level;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private Integer hitDice;
    private Integer startHealth;

    @ElementCollection
    private List<String> saves;

    @ElementCollection
    private List<String> simpleSkills;

    private Integer numberOfSimpleSkills;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "characterClass_id")
    private List<ClassSkillsPerLevel> classSkillsPerLevel;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Peculiarity> peculiarity;
}
