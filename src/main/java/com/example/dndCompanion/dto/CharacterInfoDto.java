package com.example.dndCompanion.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterInfoDto {
    private int currentHealth;
    private int allHealth;
    private  int tempHealth;
    private int speed;
    private int armorClass;
    private int experiencePoints;
    private int initiative;
    private int mastery;
}
