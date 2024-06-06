package com.example.dndCompanion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToOne
    private GameCharacter gameCharacter;
    private int currentHealth;
    private int allHealth;
    private int tempHealth;
    private int speed;
    private int armorClass;
    private int experiencePoints;
    private int initiative;
    private int mastery;
}
