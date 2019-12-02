package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Project;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.List;

public class ChangeActivityEvent implements EventProtocol {

    private List<Player> players;
    private Activity activity;
    private Project project;

    public ChangeActivityEvent(List<Player> players, Activity activity, Project project) {
        this.players = players;
        this.activity = activity;
        this.project = project;
    }

    @Override
    public void processEvent() {
        players.forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJsonResponse().toString()));


    }

    private JsonObject createJsonResponse(){
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.CHANGE_ACTIVITY.key);
        response.addProperty(GameResponseKey.COST_PROJECT.key,project.getCost());
        response.addProperty(GameResponseKey.DELAY_PROJECT.key,project.getDelay());
        response.addProperty(GameResponseKey.FAILURE_PROJECT.key,project.getFailure());
        response.addProperty(GameResponseKey.ACTIVITY_ID.key,activity.getId());
        return response;
    }
}
