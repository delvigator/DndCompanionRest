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
@Table(name = "characterRace")
public class CharacterRace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int numberFeatures;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private boolean isSub;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sub_race")
    private List<CharacterRace> subRace;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Peculiarity> peculiarities;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SkillMastery> skillMastery;

    @ElementCollection
    @Column(name = "skill_boost" )
    @MapKeyColumn(name = "skill")
    private Map<String, Integer> skillBoost;
}




