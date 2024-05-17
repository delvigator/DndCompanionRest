package com.example.checkers.controllers;

import com.example.checkers.models.CharacterClass;
import com.example.checkers.models.dto.CharacterClassDto;
import com.example.checkers.services.CharacterClassService;
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
    @GetMapping("/get")
    public ResponseEntity<List<CharacterClass>> readAll() {
        return new ResponseEntity<>(characterClassService.getAllClasses(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CharacterClass> create(@RequestBody CharacterClassDto dto) {
        return new ResponseEntity<>(characterClassService.create(dto),HttpStatus.CREATED);
    }
}
