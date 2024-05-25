package com.example.dndCompanion.dto.jwt;

import com.example.dndCompanion.entity.GameCharacter;
import com.example.dndCompanion.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
    private List<GameCharacter> characters;
    private long currentCharacter;
    private List<Item> customItems;

}
