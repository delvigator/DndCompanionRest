package com.example.dndCompanion.dto;

import com.example.dndCompanion.entity.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameCharacterDto {
        private int level;
        private String name;
        private String description;
        private String portrait;
        private CharacterRaceDto chRace;
        private SubRaceDto subRace;
        private List<CharacterClassDto> chClass;
        private String ideology;
        private List<FeatureDto> features;
        private CharacteristicDto characteristics;
        private CharacterInfoDto characterInfo;
        private List<SpellDto> knownSpells;
        private List<ItemInInventoryDto> items;
        private List<NoteDto> notes;

}
