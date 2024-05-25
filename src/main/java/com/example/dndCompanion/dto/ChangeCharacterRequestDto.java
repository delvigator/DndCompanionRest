package com.example.dndCompanion.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeCharacterRequestDto {
    private int characterIndex;
}
