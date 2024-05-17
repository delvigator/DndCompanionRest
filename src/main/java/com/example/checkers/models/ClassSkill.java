package com.example.checkers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "classSkill")
public class ClassSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String skillName;
    @Column(columnDefinition = "LONGTEXT")
    private String skillDescription;
}
