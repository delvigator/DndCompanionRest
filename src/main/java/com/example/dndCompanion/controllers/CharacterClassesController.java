package com.example.dndCompanion.controllers;

import com.example.dndCompanion.entity.CharacterClass;
import com.example.dndCompanion.dto.CharacterClassDto;
import com.example.dndCompanion.services.CharacterClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@AllArgsConstructor
public class CharacterClassesController {
    private final CharacterClassService characterClassService;
    @GetMapping("/getAll")
    public ResponseEntity<List<CharacterClass>> readAll() {
        return new ResponseEntity<>(characterClassService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CharacterClass> create(@RequestBody CharacterClassDto dto) {
        return new ResponseEntity<>(characterClassService.create(dto),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        characterClassService.delete(id);
        return HttpStatus.OK;
    }
    @PutMapping("/change")
    public ResponseEntity<CharacterClass> update(@RequestBody CharacterClass characterClass) {
        return new ResponseEntity<>(characterClassService.change(characterClass), HttpStatus.OK);
    }
}
