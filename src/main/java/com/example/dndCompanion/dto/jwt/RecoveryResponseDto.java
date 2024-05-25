package com.example.dndCompanion.dto.jwt;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecoveryResponseDto {
    private String code;
}
