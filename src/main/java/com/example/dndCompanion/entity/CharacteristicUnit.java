package com.example.dndCompanion.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int value;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SkillMastery> skillMastery;
}
