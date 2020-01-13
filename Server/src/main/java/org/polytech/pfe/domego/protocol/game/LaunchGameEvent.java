package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.calculator.InfoProjectGameCalculator;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.buying.BuyResources;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.pay.PayContract;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.ActionResponseKey;
import org.polytech.pfe.domego.protocol.game.key.ActivityResponseKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LaunchGameEvent implements EventProtocol {

    private Game game;
    private Logger logger = Logger.getGlobal();

    public LaunchGameEvent(Game game) {
        this.game = game;
    }

    @Override
    public void processEvent() {
       game.getPlayers().forEach(
               player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createUpdateResponse(player))
       );
       logger.info("LaunchGameEvent : Send Message LaunchGameEvent to all players");

    }

    public String createUpdateResponse(Player player) {
        JsonObject response = new JsonObject();

        response.addProperty(ActivityResponseKey.GAME_TYPE.key, game.getGameType().getKey());
        this.addInfosToCurrentGame(response);

        this.addPlayerObject(response, player);

        this.addPlayersMoneyObject(response, game.getPlayers());

        this.addProjectObject(response, player);

        JsonArray activitiesJson = new JsonArray();
        for (Activity activity : game.getActivities()) {
            JsonObject activityJson = new JsonObject();
            activityJson.addProperty(ActivityResponseKey.ACTIVITY_ID.key, activity.getId());
            activityJson.addProperty(ActivityResponseKey.NUMBER_OF_DAYS.key, activity.getNumberOfDays());
            activityJson.addProperty(ActivityResponseKey.TITLE.key, activity.getTitle());
            activityJson.addProperty(ActivityResponseKey.DESCRIPTION.key, activity.getDescription());
            activityJson.addProperty(ActivityResponseKey.STATUS.key, activity.getActivityStatus().toString());
            activityJson.add(ActivityResponseKey.PAYING_ACTIONS.key,this.createPayingActionsResponse(activity));
            activityJson.add(ActivityResponseKey.BUYING_ACTIONS.key,this.createBuyingActions(activity));
            activityJson.add(ActivityResponseKey.NEGOTIATION_ACTIONS.key, this.createNegotiationActionsResponse(activity));
            activityJson.add(ActivityResponseKey.PAY_CONTRACT_ACTION.key, this.createPayContractActionsResponse(activity));
            JsonArray roleIDListJson = new JsonArray();
            activity.getPayResourcesList().stream().map(PayResources::getRoleID).collect(Collectors.toSet()).forEach(roleIDListJson::add);
            activityJson.add(ActivityResponseKey.ROLE_ID_LIST.key, roleIDListJson);
            activityJson.addProperty(ActivityResponseKey.RISKS.key, activity.getRiskCardList().size());
            activitiesJson.add(activityJson);
        }
        response.add(GameResponseKey.ACTIVITIES.key, activitiesJson);
        return response.toString();
    }

    private void addInfosToCurrentGame(JsonObject response){
        response.addProperty(GameResponseKey.RESPONSE.key, GameResponseKey.LAUNCH_GAME.key);
        response.addProperty(GameResponseKey.GAME_ID.key, game.getId());
        response.addProperty(GameResponseKey.CURRENT_ACTIVITY_ID.key, game.getCurrentActivity().getId());
        response.addProperty(GameResponseKey.COST_PROJECT.key, game.getProject().getCost());
        response.addProperty(GameResponseKey.DELAY_PROJECT.key, game.getProject().getDays());
        response.addProperty(GameResponseKey.FAILURE_PROJECT.key, game.getProject().getRisks());
        response.addProperty(GameResponseKey.QUALITY_PROJECT.key, game.getProject().getQuality());
    }

    private void addPlayerObject(JsonObject response, Player player){
        JsonObject playerJson = new JsonObject();
        playerJson.addProperty(GameResponseKey.USERNAME.key, player.getName());
        playerJson.addProperty(GameResponseKey.USER_ID.key, player.getID());
        playerJson.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        playerJson.addProperty(GameResponseKey.MONEY.key, player.getMoney());
        playerJson.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getId());
        response.add(GameResponseKey.PLAYER.key, playerJson);
    }

    private void addPlayersMoneyObject(JsonObject response, List<Player> players){
        JsonArray playersJson = new JsonArray();
        for (Player player : players ) {
            JsonObject playerJson = new JsonObject();
            playerJson.addProperty(GameResponseKey.MONEY.key, player.getMoney());
            playerJson.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getId());
            playersJson.add(playerJson);
        }
        response.add(GameResponseKey.PLAYERS.key, playersJson);
    }

    private void addProjectObject(JsonObject response, Player player) {
        JsonObject projectJson = new JsonObject();
        InfoProjectGameCalculator projectGameCalculator = new InfoProjectGameCalculator(game);
        projectJson.addProperty(GameResponseKey.MIN_COST.key, projectGameCalculator.getMinResourcesToPay(player.getRole().getName()));
        projectJson.addProperty(GameResponseKey.MAX_COST.key, projectGameCalculator.getMaxResourcesToPay(player.getRole().getName()));
        projectJson.addProperty(GameResponseKey.MIN_TIME.key, projectGameCalculator.getMinTimeOfProject());
        projectJson.addProperty(GameResponseKey.MAX_TIME.key, projectGameCalculator.getMaxTimeOfProject());
        projectJson.addProperty(GameResponseKey.MIN_FAILURE.key, projectGameCalculator.getMinFailureOfProject());
        projectJson.addProperty(GameResponseKey.MAX_FAILURE.key, projectGameCalculator.getMaxFailureOfProject());

        response.add(GameResponseKey.PROJECT.key, projectJson);
    }

    private JsonArray createBuyingActions(Activity activity){
        JsonArray buyingActions = new JsonArray();
        for (BuyResources buyResources : activity.getBuyResourcesList()) {
            JsonObject buyingActionJson = new JsonObject();
            buyingActionJson.addProperty(ActionResponseKey.RATE.key, buyResources.getRate());
            buyingActionJson.addProperty(ActionResponseKey.ROLEID.key, buyResources.getRoleID());
            buyingActionJson.addProperty(ActionResponseKey.RESOURCES_GIVEN.key, buyResources.getResourcesGiven());
            buyingActions.add(buyingActionJson);

        }
        return buyingActions;
    }


    private JsonArray createPayingActionsResponse(Activity activity){
        JsonArray payingActions = new JsonArray();

        for (RoleType role : RoleType.values()) {
            JsonArray payingActionsByRole = new JsonArray();
            for (PayResources payResources: activity.getPayResourcesList().stream().filter(payResources -> payResources.getRoleID() == role.getId()).sorted(Comparator.naturalOrder()).collect(Collectors.toList())) {

                JsonObject payingActionJson = new JsonObject();

                JsonArray payActionsForType = new JsonArray();
                payResources.getPriceAndBonusMap().forEach((price,bonus)-> {
                    JsonObject payActionJson = new JsonObject();
                    payActionJson.addProperty(ActionResponseKey.AMOUNT_TO_PAY.key,price);
                    payActionJson.addProperty(ActionResponseKey.BONUS_AMOUNT.key,bonus);
                    payActionsForType.add(payActionJson);

                });
                payingActionJson.add(ActionResponseKey.ACTIONS.key, payActionsForType);
                payingActionJson.addProperty(ActionResponseKey.ROLEID.key, payResources.getRoleID());
                payingActionJson.addProperty(ActionResponseKey.PAY_TYPE.key, payResources.getPayResourceType().toString());
                payingActionsByRole.add(payingActionJson);
            }
            if (payingActionsByRole.size() != 0)
            {

                JsonObject payingAction = new JsonObject();
                payingAction.addProperty(ActionResponseKey.ROLEID.key, role.getId());
                payingAction.add(ActionResponseKey.ACTIONS.key, payingActionsByRole);
                payingActions.add(payingAction);
            }
        }
        return payingActions;
    }

    private JsonArray createNegotiationActionsResponse(Activity activity){
        JsonArray negotiationActions = new JsonArray();
        for(Negotiation negotiation : activity.getNegotiationList()){
            JsonObject negotiationAction = new JsonObject();
            negotiationAction.addProperty(ActionResponseKey.NEGOTIATIONID.key, negotiation.getId());
            negotiationAction.addProperty(ActionResponseKey.GIVERID.key, negotiation.getGiverRoleID());
            negotiationAction.addProperty(ActionResponseKey.RECEIVERID.key, negotiation.getReceiverRoleID());
            negotiationActions.add(negotiationAction);
        }
        return negotiationActions;
    }

    private JsonArray createPayContractActionsResponse(Activity activity){
        JsonArray payContractActions = new JsonArray();
        for(PayContract payContract : activity.getPayContractList()){
            JsonObject payContractAction = new JsonObject();
            payContractAction.addProperty(GameResponseKey.AMOUNT.key, payContract.getPercentage());
            payContractAction.addProperty(ActionResponseKey.GIVERID.key, payContract.getNegotiation().getGiverRoleID());
            payContractAction.addProperty(ActionResponseKey.RECEIVERID.key, payContract.getNegotiation().getReceiverRoleID());
            payContractActions.add(payContractAction);
        }
        return payContractActions;
    }
}