package com.example.dndCompanion.controllers;

import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.dto.SpellDto;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.entity.Spell;
import com.example.dndCompanion.services.SpellService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spells")
@AllArgsConstructor
public class SpellController {
    private final SpellService spellService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Spell>> readAll() {
        return new ResponseEntity<>(spellService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Spell> create(@RequestBody SpellDto dto) {
        return new ResponseEntity<>(spellService.create(dto),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        spellService.delete(id);
        return HttpStatus.OK;
    }
    @PutMapping("/change")
    public ResponseEntity<Spell> update(@RequestBody Spell spell) {
        return new ResponseEntity<>(spellService.change(spell), HttpStatus.OK);
    }
}
