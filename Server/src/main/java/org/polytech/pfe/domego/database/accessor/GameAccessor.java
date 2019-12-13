package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.polytech.pfe.domego.generator.GameGenerator;
import org.polytech.pfe.domego.generator.GameType;
import org.polytech.pfe.domego.generator.initial.InitialGameGenerator;
import org.polytech.pfe.domego.generator.intermediate.IntermediateGameGenerator;
import org.polytech.pfe.domego.models.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Game createNewGameFromRoom(Room room, GameType gameType){
        List<Player> players = new ArrayList<>();
        for (Player player : room.getPlayerList()) {
            players.add(new Player(player));
        }
        GameGenerator gameGenerator;
        if (gameType.equals(GameType.INITIAL))
            gameGenerator = new InitialGameGenerator();
        else
            gameGenerator = new IntermediateGameGenerator();
        GameGenerator initialGameGenerator = new InitialGameGenerator();
        Game game = new Game(room.getID(), players, initialGameGenerator.getAllActivitiesOfTheGame(), initialGameGenerator.getCostWanted(), initialGameGenerator.getNumberOfDaysWanted(), initialGameGenerator.getNumberOfRisksDrawnWanted(), gameType);
        gameInstance.addGame(game);
        return game;
    }
}
