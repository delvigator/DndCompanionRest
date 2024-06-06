package com.example.dndCompanion.controllers;

import com.example.dndCompanion.dto.CharacterRaceDto;
import com.example.dndCompanion.entity.CharacterRace;
import com.example.dndCompanion.services.CharacterRaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
@AllArgsConstructor
public class CharacterRacesController {
    @Autowired
    CharacterRaceService characterRaceService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CharacterRace>> readAll() {
        return new ResponseEntity<>(characterRaceService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CharacterRace> create(@RequestBody CharacterRaceDto dto) {
        return new ResponseEntity<>(characterRaceService.create(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        characterRaceService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/change")
    public ResponseEntity<CharacterRace> update(@RequestBody CharacterRace characterRace) {
        return new ResponseEntity<>(characterRaceService.change(characterRace), HttpStatus.OK);
    }
}
