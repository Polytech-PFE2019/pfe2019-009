package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.game.RiskCard;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.models.risk.RiskAction;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DrawRiskCardEvent implements EventProtocol {

    private Game game;
    private final Logger logger = Logger.getGlobal();

    public DrawRiskCardEvent(Game game) {
        this.game = game;
    }

    @Override
    public void processEvent() {
        Activity currentActivity = this.game.getCurrentActivity();
        List<RiskCard> risks = currentActivity.getRiskCardList();
        logger.log(Level.INFO, "DrawRiskCardEvent : In the game {0} for the activity {1} they draw {2} cards" , new Object[]{game.getId(), currentActivity.getId(), risks.size()});
        for (RiskCard risk : risks) {
            if (!risk.isDraw())
                risk.doAction(game);
        }


        JsonObject response = createJsonResponse(currentActivity,risks.stream().map(riskCard -> riskCard.getRiskAction()).collect(Collectors.toList()));
        game.getPlayers().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(finalResponseWithPlayerElement(response, player).toString()));

        if (currentActivity.allMandatoryResourcesHaveBeenPayed())//Check if someOne has risk card to pay
            currentActivity.finishActivity();

        if(currentActivity.getActivityStatus().equals(ActivityStatus.FINISHED))
            new ChangeActivityEvent(game).processEvent();
    }

    private JsonObject createJsonResponse(Activity activity, List<RiskAction> riskActions) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.DRAW_RISK.key);
        response.addProperty(GameResponseKey.RISK_OF_ACTIVITY_ID.key, activity.getId());
        JsonArray risksArray = new JsonArray();
        for (RiskAction riskAction : riskActions) {
            JsonObject riskJson = new JsonObject();
            JsonArray bonusJson = new JsonArray();
            riskAction.getBonusList().forEach(bonus -> bonusJson.add(bonus.transformToJson()));
            riskJson.add(GameResponseKey.BONUS.key, bonusJson);
            riskJson.addProperty(GameResponseKey.DESCRIPTION.key, riskAction.getDescription());
            riskJson.addProperty(GameResponseKey.RISK_OF_ACTIVITY_ID.key, riskAction.getRiskOfActivityId());
            risksArray.add(riskJson);
        }
        response.add(GameResponseKey.RISKS.key, risksArray);
        return response;
    }

    private JsonObject finalResponseWithPlayerElement(JsonObject response, Player player){
        JsonObject playerJson = new JsonObject();
        playerJson.addProperty(GameResponseKey.USERNAME.key, player.getName());
        playerJson.addProperty(GameResponseKey.USER_ID.key, player.getID());
        playerJson.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        playerJson.addProperty(GameResponseKey.MONEY.key, player.getMoney());
        playerJson.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getId());

        response.add(GameResponseKey.PLAYER.key, playerJson);
        return response;

    }
}
