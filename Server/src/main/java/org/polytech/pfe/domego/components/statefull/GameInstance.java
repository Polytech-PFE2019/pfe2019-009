package org.polytech.pfe.domego.components.statefull;


import org.polytech.pfe.domego.components.business.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameInstance {

    private List<Game> gameList;

    private GameInstance() {
        this.gameList = new ArrayList<>();
    }

    private static GameInstance INSTANCE = new GameInstance();

    public static GameInstance getInstance() {
        return INSTANCE;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public int countGame(){
        return gameList.size();
    }

    public Optional<Game> getSpecificGameByID(String id){
        return gameList.stream().filter(game -> game.getId().equals(id)).findFirst();
    }

    public boolean addGame(Game game){
        return this.gameList.add(game);
    }

    public boolean removeGame(Game game){
        return this.gameList.remove(game);
    }
}
