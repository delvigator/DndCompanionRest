package com.example.dndCompanion.dto;

import com.example.dndCompanion.entity.Item;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemInInventoryDto {
    private Item item;
    private int number;
    private boolean equip;
}
