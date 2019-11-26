package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.PayResources;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.BuyResources;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.ActionResponseKey;
import org.polytech.pfe.domego.protocol.game.key.ActivityResponseKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.polytech.pfe.domego.protocol.room.key.RoomResponseKey;

public class UpdateGameEvent implements EventProtocol {

    private Game game;

    public UpdateGameEvent(Game game) {
        this.game = game;
    }

    @Override
    public void processEvent() {
        for (Player player : game.getPlayers()) {
            new Messenger(player.getSession()).sendSpecificMessageToAUser(createUpdateResponse(player.getID()));
        }
    }

    private String createUpdateResponse(String userID) {

        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, RoomResponseKey.UPDATE.key);
        response.addProperty(GameResponseKey.GAMEID.key, game.getId());

        JsonObject playerJson = new JsonObject();
        Player player = game.getPlayerById(userID).get();
        playerJson.addProperty(GameResponseKey.USERNAME.key, player.getName());
        playerJson.addProperty(GameResponseKey.USERID.key, player.getID());
        playerJson.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        playerJson.addProperty(GameResponseKey.MONEY.key, player.getMoney());
        playerJson.addProperty(GameResponseKey.ROLEID.key, player.getRole().getId());


        response.addProperty(GameResponseKey.PLAYER.key, playerJson.toString());

        JsonArray activitiesJson = new JsonArray();
        for (Activity activity : game.getActivities()) {
            JsonObject activityJson = new JsonObject();
            activityJson.addProperty(ActivityResponseKey.ACTIVITY_ID.key, activity.getId());
            activityJson.addProperty(ActivityResponseKey.NUMBER_OF_DAYS.key, activity.getNumberOfDays());
            activityJson.addProperty(ActivityResponseKey.DESCRIPTION.key, activity.getDescription());
            activityJson.addProperty(ActivityResponseKey.STATUS.key, activity.getActivityStatus().toString());

            JsonArray payingActions = new JsonArray();
            for (PayResources payResources : activity.getPayResourcesList()) {

                JsonObject payingActionJson = new JsonObject();
                payingActionJson.addProperty(ActionResponseKey.STATUS.key, payResources.hasPaid());
                payingActionJson.addProperty(ActionResponseKey.AMOUNT_TO_PAY.key, payResources.getAmountNeeded());
                payingActionJson.addProperty(ActionResponseKey.ROLEID.key, payResources.getRoleID());
                payingActionJson.addProperty(ActionResponseKey.PAY_TYPE.key, payResources.getPayResourceType().toString());
                payingActionJson.addProperty(ActionResponseKey.BONUS_AMOUNT.key, payResources.getBonusGiven());
                payingActions.add(payingActionJson);


            }
            activityJson.addProperty(ActivityResponseKey.PAYING_ACTIONS.key,payingActions.toString());

            JsonArray buyingActions = new JsonArray();
            for (BuyResources buyResources : activity.getBuyResourcesList()) {
                if(player.getRole().getId() == buyResources.getRoleID()) {
                    JsonObject buyingActionJson = new JsonObject();
                    buyingActionJson.addProperty(ActionResponseKey.STATUS.key, buyResources.hasPaid());
                    buyingActionJson.addProperty(ActionResponseKey.RATE.key, buyResources.getRate());
                    buyingActionJson.addProperty(ActionResponseKey.ROLEID.key, buyResources.getRoleID());
                    buyingActionJson.addProperty(ActionResponseKey.RESOURCES_GIVEN.key, buyResources.getResourcesGiven());
                    buyingActionJson.addProperty(ActionResponseKey.MONEY_PAID.key, buyResources.getAmountPaid());
                    buyingActions.add(buyingActionJson);
                }
            }
            activityJson.addProperty(ActivityResponseKey.BUYING_ACTIONS.key,buyingActions.toString());
            activitiesJson.add(activityJson);
        }
        response.addProperty(GameResponseKey.ACTIVITIES.key, activitiesJson.toString());

        return response.toString();
    }
}
