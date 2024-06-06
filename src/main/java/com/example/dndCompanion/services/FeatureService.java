package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.FeatureDto;
import com.example.dndCompanion.dto.PeculiarityDto;
import com.example.dndCompanion.entity.Feature;
import com.example.dndCompanion.entity.Peculiarity;
import com.example.dndCompanion.repository.FeatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FeatureService {
    @Autowired
   FeatureRepository featureRepository;

    public List<Feature> getAll(){
        return featureRepository.findAll();
    }
    public Feature create(FeatureDto dto){
        List<Peculiarity> peculiarities=new ArrayList<>();
        for(PeculiarityDto i:dto.getPeculiarities()){
            peculiarities.add(Peculiarity.builder()
                            .text(i.getText())
                            .title(i.getTitle())
                    .build());
        }
        return featureRepository.save(Feature.builder()
                .bonuses(dto.getBonuses())
                .name(dto.getName())
                .description(dto.getDescription())
                .peculiarities(peculiarities)
                .build());
    }
    public void delete(Long id){
        featureRepository.deleteById(id);
    }
    public Feature change(Feature feature){
        return featureRepository.save(feature);
    }
}
