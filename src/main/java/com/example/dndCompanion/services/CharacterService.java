package com.example.dndCompanion.services;


import com.example.dndCompanion.dto.*;
import com.example.dndCompanion.entity.*;
import com.example.dndCompanion.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CharacterService {
    @Autowired
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;
    private final CharacterClassRepository classRepository;
    private final CharacterRaceRepository characterRaceRepository;
    private final FeatureRepository featureRepository;
    private final SubRaceRepository subRaceRepository;
    private final UserService userService;

    public List<GameCharacter> getAllByUser(String username) {
        List<GameCharacter> characters=characterRepository.findAll();
        List<GameCharacter> result=new ArrayList<>();
        for(GameCharacter character:characters){
            if(userService.findByUsername(username).isPresent() && username.equals(character.getUserName()))
            { result.add(character);}
        }
        return result;
    }
    public List<GameCharacter> getAllByUser(Principal principal) {
        List<GameCharacter> characters=characterRepository.findAll();
        List<GameCharacter> result=new ArrayList<>();
        for(GameCharacter character:characters){
            if(userService.getUserByPrincipal(principal).getUsername().equals(character.getUserName()))
            { result.add(character);}
        }
        return result;
    }
public HttpStatus change(GameCharacter character, Principal principal){
        character.setUserName(userService.getUserByPrincipal(principal).getUsername());
    characterRepository.save(character);
        return HttpStatus.OK;
}
public void delete(Long id,Principal principal){
        if(userService.getUserByPrincipal(principal).getUsername().equals(characterRepository.getById(id).getUserName()))
        {characterRepository.deleteById(id);}
}

    public GameCharacter create(GameCharacterDto dto,Principal principal) {
        CharacterInfoDto characterInfoDto = dto.getCharacterInfo();
        List<CharacteristicUnit> characteristicUnits = new ArrayList<>();
        for (CharacteristicUnitDto i : dto.getCharacteristics().getCharacteristicsList()) {
            List<SkillMastery> skills = new ArrayList<>();
            for (SkillMasteryDto j : i.getSkills()) {
                skills.add(SkillMastery.builder()
                        .name(j.getName())
                        .mastery(j.isMastery())
                        .build());
            }
            characteristicUnits.add(CharacteristicUnit.builder()
                    .value(i.getValue())
                    .name(i.getName())
                    .skills(skills)
                    .build());
        }
        Characteristic characteristic = Characteristic.builder()
                .characteristicsList(characteristicUnits)
                .build();

        List<ItemInInventory> itemsInInventory = new ArrayList<>();
        for (ItemInInventoryDto i : dto.getItems()) {
            itemsInInventory.add(ItemInInventory.builder()
                    .equip(i.isEquip())
                    .number(i.getNumber())
                    .item(Item.builder()
                            .type(i.getItem().getType())
                            .description(i.getItem().getDescription())
                            .equipable(i.getItem().isEquipable())
                            .rarity(i.getItem().getRarity())
                            .weight(i.getItem().getWeight())
                            .name(i.getItem().getName())
                            .build())
                    .build());
        }
        List<Note> notes = new ArrayList<>();
        for (NoteDto i : dto.getNotes()) {
            notes.add(Note.builder()
                    .category(i.getCategory())
                    .title(i.getTitle())
                    .text(i.getText())
                    .build());
        }
        List<CharacterClass> classes = new ArrayList<>();
        for (CharacterClassDto i : dto.getChClass()) {
            classes.add(classRepository.findByName(i.getName()));
        }
        List<Feature> features = new ArrayList<>();
        for (FeatureDto i : dto.getFeatures()) {
            features.add(featureRepository.findByName(i.getName()));
        }
        List<Spell> spells = new ArrayList<>();
        for (SpellDto i : dto.getKnownSpells()) {
            spells.add(Spell.builder()
                            .name(i.getName())
                            .classes(i.getClasses())
                            .level(i.getLevel())
                            .description(i.getDescription())
                            .school(i.getSchool())
                            .distance(i.getDistance())
                            .isRitual(i.isRitual())
                            .spellComponents(i.getSpellComponents())
                            .timeAction(i.getTimeAction())
                            .timeApplication(i.getTimeApplication())
                                    .build());
        }
        log.info(userService.getUserByPrincipal(principal).getUsername());
        GameCharacter character= GameCharacter.builder()
                .userName(userService.getUserByPrincipal(principal).getUsername())
                .name(dto.getName())
                .level(dto.getLevel())
                .characterInfo(CharacterInfo.builder()
                        .allHealth(characterInfoDto.getAllHealth())
                        .armorClass(characterInfoDto.getArmorClass())
                        .currentHealth(characterInfoDto.getCurrentHealth())
                        .experiencePoints(characterInfoDto.getExperiencePoints())
                        .speed(characterInfoDto.getSpeed())
                        .mastery(characterInfoDto.getMastery())
                        .initiative(characterInfoDto.getInitiative())
                        .tempHealth(characterInfoDto.getTempHealth())
                        .build())
                .characteristics(characteristic)
                .description(dto.getDescription())
                .items(itemsInInventory)
                .notes(notes)
                .chClass(classes)
                .chRace(characterRaceRepository.findByName(dto.getChRace().getName()))
                .features(features)
                .knownSpells(spells)
                .ideology(dto.getIdeology())
                .portrait(dto.getPortrait())
                .build();
        if(dto.getSubRace()!=null) character.setSubRace(subRaceRepository.findByName(dto.getSubRace().getName()));
        characterRepository.save(character);
        return  character;
    }
}
