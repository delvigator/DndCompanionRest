package com.example.dndCompanion.dto;

import lombok.*;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteDto {
    private String title;
    private String text;
    private String category;
}
