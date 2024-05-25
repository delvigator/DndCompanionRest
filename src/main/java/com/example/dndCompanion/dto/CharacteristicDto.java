package com.example.dndCompanion.dto;

import com.example.dndCompanion.entity.CharacteristicUnit;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacteristicDto {
    List<CharacteristicUnitDto> characteristicsList;
}
