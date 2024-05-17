//package com.example.checkers.models;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@Table(name = "games")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Game {
//    @Id
//    @GeneratedValue
//    @Column(name = "id")
//    private int id;
//    @Column(name = "title")
//    private String title;
//    @Column(name = "recording")
//    private String recording;
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinColumn
//    private User user;
//    private LocalDateTime dateOfCreated;
//    @PrePersist
//    private void init() {
//        dateOfCreated = LocalDateTime.now();
//    }
//
//
//
//
//}
