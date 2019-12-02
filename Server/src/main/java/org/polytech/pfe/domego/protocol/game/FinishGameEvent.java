package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinishGameEvent implements EventProtocol {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Game game;

    public FinishGameEvent(Game game) {
        this.game = game;
    }


    @Override
    public void processEvent() {

        logger.log(Level.INFO,"Game : The game {0} is now Finished", this.game.getId());
        this.game.getPlayers().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJsonResponse().toString()));

    }

    private JsonObject createJsonResponse() {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.FINISH.key);
        JsonArray ranking = new JsonArray();
        for (Player player : this.game.getPlayers()) {
            JsonObject rankingPlayer = new JsonObject();
            rankingPlayer.addProperty(GameResponseKey.NOVP.key,player.getVictoryPoints());
            rankingPlayer.addProperty(GameResponseKey.RANK.key,player.getRole().getId());
            JsonObject playerJSON = new JsonObject();
            playerJSON.addProperty(GameResponseKey.USERNAME.key, player.getName());
            playerJSON.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getName().getId());
            rankingPlayer.add(GameResponseKey.PLAYER.key,playerJSON);
        }
        response.add(GameResponseKey.RANKING.key, ranking);
        return response;
    }
}
