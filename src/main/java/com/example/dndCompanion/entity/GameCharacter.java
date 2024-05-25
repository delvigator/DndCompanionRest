package com.example.dndCompanion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int level;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String portrait;
    @OneToOne(cascade = CascadeType.ALL)
    private CharacterRace chRace;
    @OneToOne(cascade = CascadeType.ALL)
    private SubRace subRace;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "character_id")
    private List<CharacterClass> chClass;
    private String ideology;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "character_id")
    private List<Feature> features;
    @OneToOne(cascade = CascadeType.ALL)
    private Characteristic characteristics;
    @OneToOne(cascade = CascadeType.ALL)
    private CharacterInfo characterInfo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "character_id")
    private List<Spell> knownSpells;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "character_id")
    private List<ItemInInventory> items;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "character_id")
    private List<Note> notes;
    private String userName;
}
