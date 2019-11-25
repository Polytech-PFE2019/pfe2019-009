package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.components.buisness.Game;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameAccessor {

    private GameInstance gameInstance;

    public GameAccessor() {
        gameInstance = GameInstance.getInstance();
    }

    public Optional<Game> getGameById(String id){return gameInstance.getSpecificGameByID(id);}

    public List<Game> getAllOfGame(){
        return gameInstance.getGameList();
    }

    public int numberOfGame(){
        return gameInstance.countGame();
    }
}
