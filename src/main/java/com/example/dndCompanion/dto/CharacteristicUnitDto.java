package com.example.dndCompanion.dto;

import com.example.dndCompanion.entity.SkillMastery;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacteristicUnitDto {
    private String name;
    private int value;
    private List<SkillMasteryDto> skillMastery;
}
