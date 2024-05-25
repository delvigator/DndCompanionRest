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
public class Spell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String distance;
    @ElementCollection
    private List<String> classes;
    private String school;
    private int level;
    private boolean isRitual;
    @ElementCollection
    private Map<String, Boolean> spellComponents;
    private String timeApplication;
    private String timeAction;
}