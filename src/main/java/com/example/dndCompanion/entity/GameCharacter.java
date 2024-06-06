package com.example.dndCompanion.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int level;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String portrait;
    @OneToOne
    private CharacterRace chRace;
    @OneToOne
    private CharacterRace subRace;
    @OneToMany
    private List<CharacterClass> chClass;
    private String ideology;
    @OneToMany
    private List<Feature> features;
    @OneToOne(cascade = CascadeType.ALL)
    private Characteristic characteristics;
    @OneToOne(cascade = CascadeType.ALL)
    private CharacterInfo characterInfo;
    @OneToMany
    private List<Spell> knownSpells;
    @OneToMany
    @JoinColumn(name = "character_id")
    private List<ItemInInventory> items;
    @OneToMany
    @JoinColumn(name = "character_id")
    private List<Note> notes;
    private String userName;
}
