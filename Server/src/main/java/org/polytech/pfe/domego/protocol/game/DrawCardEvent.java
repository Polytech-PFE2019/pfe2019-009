package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.calculator.InfoProjectGameCalculator;
import org.polytech.pfe.domego.components.game.card.QualityCard;
import org.polytech.pfe.domego.components.game.card.RiskCard;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.QualityAction;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.models.activity.PayResourceType;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.models.risk.RiskAction;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DrawCardEvent implements EventProtocol {

    private Game game;
    private final Logger logger = Logger.getGlobal();

    public DrawCardEvent(Game game) {
        this.game = game;

    }

    @Override
    public void processEvent() {
        Activity currentActivity = this.game.getCurrentActivity();
        for (RiskCard risk : currentActivity.getRiskCardList()) {
            if (!risk.isDraw())
                risk.doAction(game);
        }

        int numberOfQuality = currentActivity.getPayResourcesList().stream().filter(payResources -> payResources.getPayResourceType().equals(PayResourceType.QUALITY)).mapToInt(PayResources::getBonusGiven).sum();
        for (int i = 0; i < numberOfQuality; i++) {
            if (!currentActivity.getQualityCards().get(i).isDraw())
                currentActivity.getQualityCards().get(i).doAction(game);
        }
        List<RiskCard> risks = currentActivity.getRiskCardList().stream().filter(riskCard -> riskCard.isDraw()).collect(Collectors.toList());
        List<QualityCard> qualities = currentActivity.getQualityCards().stream().filter(qualityCard -> qualityCard.isDraw()).collect(Collectors.toList());
        logger.log(Level.INFO, "DrawCardEvent : In the game {0} for the activity {1} they draw {2} risk cards and {3} quality card" , new Object[]{game.getId(), currentActivity.getId(), risks.size(), qualities.size() });
        logger.log(Level.INFO, "DrawCardEvent : List of Risk : {0}" , risks.stream().map(riskCard -> riskCard.getRiskAction()).collect(Collectors.toList()));
        logger.log(Level.INFO, "DrawCardEvent : List of Quality : {0}" , qualities.stream().map(qualityCard -> qualityCard.getQualityAction()).collect(Collectors.toList()));
        JsonObject response = createJsonResponse(currentActivity,risks.stream().map(riskCard -> riskCard.getRiskAction()).collect(Collectors.toList()), qualities.stream().map(qualityCard -> qualityCard.getQualityAction()).collect(Collectors.toList()));
        game.getPlayers().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(finalResponseWithPlayerElement(response, player).toString()));

        if (currentActivity.allMandatoryResourcesHaveBeenPayed())//Check if someOne has risk card to pay
            currentActivity.finishActivity();

        if(currentActivity.getActivityStatus().equals(ActivityStatus.FINISHED))
            new ChangeActivityEvent(game).processEvent();
    }

    private JsonObject createJsonResponse(Activity activity, List<RiskAction> riskActions, List<QualityAction> qualityActions) {
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

        JsonArray qualitiesArray = new JsonArray();
        for (QualityAction qualityAction : qualityActions) {
            JsonObject qualityJson = new JsonObject();
            qualityJson.add(GameResponseKey.BONUS.key, qualityAction.getBonus().transformToJson());
            qualityJson.addProperty(GameResponseKey.DESCRIPTION.key, qualityAction.getDescription());
            qualityJson.addProperty(GameResponseKey.QUALITY_OF_ACTIVITY_ID.key, qualityAction.getQualityOfActivityId());
            qualitiesArray.add(qualityJson);
        }


        response.add(GameResponseKey.RISKS.key, risksArray);
        response.add(GameResponseKey.QUALITIES.key, qualitiesArray);
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
        InfoProjectGameCalculator infoProjectGameCalculator = new InfoProjectGameCalculator(this.game);
        JsonObject projectJson = new JsonObject();
        projectJson.addProperty(GameResponseKey.MIN_COST.key, infoProjectGameCalculator.getMinResourcesToPay(player.getRole().getName()));
        projectJson.addProperty(GameResponseKey.MAX_COST.key, infoProjectGameCalculator.getMaxResourcesToPay(player.getRole().getName()));
        projectJson.addProperty(GameResponseKey.MIN_TIME.key, infoProjectGameCalculator.getMinTimeOfProject());
        projectJson.addProperty(GameResponseKey.MAX_TIME.key, infoProjectGameCalculator.getMaxTimeOfProject());
        projectJson.addProperty(GameResponseKey.MIN_FAILURE.key, infoProjectGameCalculator.getMinFailureOfProject());
        projectJson.addProperty(GameResponseKey.MAX_FAILURE.key, infoProjectGameCalculator.getMaxFailureOfProject());

        response.add(GameResponseKey.PROJECT.key, projectJson);
        return response;

    }
}
