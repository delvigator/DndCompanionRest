package com.example.dndCompanion.controllers;


import com.example.dndCompanion.dto.FeatureDto;
import com.example.dndCompanion.entity.Feature;
import com.example.dndCompanion.services.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/features")
@AllArgsConstructor
public class FeaturesController {
    @Autowired
    FeatureService featureService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Feature>> readAll() {
        return new ResponseEntity<>(featureService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Feature> create(@RequestBody FeatureDto dto) {
        return new ResponseEntity<>(featureService.create(dto),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        featureService.delete(id);
        return HttpStatus.OK;
    }
    @PutMapping("/change")
    public ResponseEntity<Feature> update(@RequestBody Feature feature) {
        return new ResponseEntity<>(featureService.change(feature), HttpStatus.OK);
    }
}
