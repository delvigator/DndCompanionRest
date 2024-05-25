package com.example.dndCompanion.controllers;

import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {
   private final ItemService itemService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Item>> readAll() {
        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Item> create(@RequestBody ItemDto dto) {
        return new ResponseEntity<>(itemService.create(dto),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        itemService.delete(id);
        return HttpStatus.OK;
    }
    @PutMapping("/change")
    public ResponseEntity<Item> update(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.change(item), HttpStatus.OK);
    }
}
