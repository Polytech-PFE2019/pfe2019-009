package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeActivityEvent implements EventProtocol {

    private Game game;
    private final Logger logger = Logger.getGlobal();

    public ChangeActivityEvent(Game game) {
        this.game = game;
    }

    @Override
    public void processEvent() {
        game.goToTheNextActivity();
        logger.log(Level.INFO, "ChangeActivityEvent : In game : {0}, the current activity is now {1}", new Object[]{game.getId(), game.getCurrentActivity().getId()});
        game.getPlayers().parallelStream().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJsonResponse().toString()));
    }

    private JsonObject createJsonResponse(){
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.CHANGE_ACTIVITY.key);
        response.addProperty(GameResponseKey.COST_PROJECT.key,game.getProject().getCost());
        response.addProperty(GameResponseKey.DELAY_PROJECT.key,game.getProject().getDelay());
        response.addProperty(GameResponseKey.FAILURE_PROJECT.key,game.getProject().getFailure());
        response.addProperty(GameResponseKey.ACTIVITY_ID.key,game.getCurrentActivity().getId());
        return response;
    }
}
