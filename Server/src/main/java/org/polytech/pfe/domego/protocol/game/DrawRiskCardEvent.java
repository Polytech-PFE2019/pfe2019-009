package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.game.RiskCard;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.models.risk.RiskAction;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.List;
import java.util.stream.Collectors;

public class DrawRiskCardEvent implements EventProtocol {

    private Game game;

    public DrawRiskCardEvent(Game game) {
        this.game = game;
    }

    @Override
    public void processEvent() {
        Activity currentActivity = this.game.getCurrentActivity();
        List<RiskCard> risks = currentActivity.getRiskCardList();
        for (RiskCard risk : risks) {
            if (!risk.isDraw())
                risk.doAction(game);
        }

        if (currentActivity.allMandatoryResourcesHaveBeenPayed())//Check if someOne has risk card to pay
            currentActivity.finishActivity();
        String response = createJsonResponse(risks.stream().map(riskCard -> riskCard.getRiskAction()).collect(Collectors.toList())).toString();
        game.getPlayers().parallelStream().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(response));

        if(currentActivity.getActivityStatus().equals(ActivityStatus.FINISHED)){
            if(currentActivity.getId() == game.getActivities().size())
                new FinishGameEvent(game).processEvent();
            else{
                new ChangeActivityEvent(game).processEvent();
            }
        }
    }

    private JsonObject createJsonResponse(List<RiskAction> riskActions) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.DRAW_RISK.key);
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

        return response;

    }
}
