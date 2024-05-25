package com.example.dndCompanion.controllers;

import com.example.dndCompanion.dto.CharacterRaceDto;
import com.example.dndCompanion.dto.GameCharacterDto;
import com.example.dndCompanion.entity.CharacterRace;
import com.example.dndCompanion.entity.GameCharacter;
import com.example.dndCompanion.services.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/characters")
@AllArgsConstructor
public class CharacterController {
    private  final CharacterService characterService;
    @GetMapping("/getAll")
    public ResponseEntity<List<GameCharacter>> readAllByUser( Principal principal) {
        return new ResponseEntity<>(characterService.getAllByUser(principal), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<GameCharacter> create(@RequestBody GameCharacterDto dto, Principal principal) {
        return new ResponseEntity<>(characterService.create(dto,principal),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id,Principal principal) {
        characterService.delete(id,principal);
        return HttpStatus.OK;
    }
    @PutMapping("/change")
    public ResponseEntity<GameCharacter> update(@RequestBody GameCharacter gameCharacter, Principal principal) {
        return new ResponseEntity<>(characterService.change(gameCharacter,principal));
    }
}
