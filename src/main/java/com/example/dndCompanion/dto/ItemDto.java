package com.example.dndCompanion.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDto {
    private String name;
    private int weight;
    private String type;
    private boolean equipable;
    private String description;
    private String rarity;

    // getters and setters
}
