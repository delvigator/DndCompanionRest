package com.example.checkers.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "classInfo")
public class ClassInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@Column(columnDefinition = "LONGTEXT")
    private String description;
    private Integer hitDice;
    private Integer startHealth;

    @ElementCollection
    private List<String> saves;

    @ElementCollection
    private List<String> simpleSkills;

    private Integer numberOfSimpleSkills;
}
