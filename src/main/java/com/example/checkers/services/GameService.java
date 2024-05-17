//package com.example.checkers.services;
//
//import com.example.checkers.models.Game;
//import lombok.Getter;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class GameService {
//    @Getter
//    private final List<Game> games=new ArrayList<>();
//    private final long id=0;
//
//    public void saveGame(Game game){
//        games.add(game);
//    }
//    public void deleteGame(long id){
//        games.removeIf(game -> game.getId()==id);
//    }
//}
