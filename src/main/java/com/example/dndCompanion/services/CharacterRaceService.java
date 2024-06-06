package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.CharacterRaceDto;
import com.example.dndCompanion.dto.PeculiarityDto;
import com.example.dndCompanion.dto.SkillMasteryDto;
import com.example.dndCompanion.dto.SubRaceDto;
import com.example.dndCompanion.entity.CharacterRace;
import com.example.dndCompanion.entity.Peculiarity;
import com.example.dndCompanion.entity.SkillMastery;

import com.example.dndCompanion.repository.CharacterRaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CharacterRaceService {

   private final CharacterRaceRepository characterRaceRepository;
  //  private  final SubRaceRepository subRaceRepository;
    public List<CharacterRace> getAll(){
        return characterRaceRepository.findAll();
    }
    public CharacterRace create(CharacterRaceDto dto){
        return characterRaceRepository.save(getFromDto(dto));
    }
    public void delete(Long id){
        characterRaceRepository.deleteById(id);
    }
    public CharacterRace change(CharacterRace characterRace){
        return characterRaceRepository.save(characterRace);
    }
    public CharacterRace getFromDto(CharacterRaceDto dto){
        List<CharacterRace> subRaces=new ArrayList<>();
        for( CharacterRaceDto i :dto.getSubRace()){
            List<Peculiarity> peculiarities=new ArrayList<>();
            for(PeculiarityDto j: i.getPeculiarities()){
                peculiarities.add(Peculiarity.builder()
                                .title(j.getTitle())
                                .text(j.getText())
                        .build());
            }
            List<SkillMastery> skillMasteryList=new ArrayList<>();
            for(SkillMasteryDto j : i.getSkillMastery()){
                skillMasteryList.add(SkillMastery.builder()
                                .name(j.getName())
                                .mastery(j.isMastery())
                        .build());
            }
            subRaces.add(CharacterRace.builder()
                            .isSub(true)
                            .name(i.getName())
                            .description(i.getDescription())
                            .peculiarities(peculiarities)
                            .skillMastery(skillMasteryList)
                            .skillBoost(i.getSkillBoost())
                    .build());
        }
        characterRaceRepository.saveAll(subRaces);

        List<Peculiarity> peculiarities=new ArrayList<>();
        for(PeculiarityDto j: dto.getPeculiarities()){
            peculiarities.add(Peculiarity.builder()
                    .title(j.getTitle())
                    .text(j.getText())
                    .build());
        }
        List<SkillMastery> skillMasteryList=new ArrayList<>();
        for(SkillMasteryDto j : dto.getSkillMastery()){
            skillMasteryList.add(SkillMastery.builder()
                    .name(j.getName())
                    .mastery(j.isMastery())
                    .build());
        }
        return CharacterRace.builder()
                .isSub(false)
                .name(dto.getName())
                .numberFeatures(dto.getNumberFeatures())
                .skillBoost(dto.getSkillBoost())
                .description(dto.getDescription())
                .subRace(subRaces)
                .peculiarities(peculiarities)
                .skillMastery(skillMasteryList)

                .build();
    }
}
