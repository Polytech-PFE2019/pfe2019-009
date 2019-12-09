package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.Optional;
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
        if (game.getCurrentActivity().getId() == game.getActivities().size()){
            new FinishGameEvent(game).processEvent();
            return;
        }
        else{
            game.goToTheNextActivity();
            new PayContractEvent(game).processEvent();
            logger.log(Level.INFO, "ChangeActivityEvent : In game : {0}, the current activity is now {1}", new Object[]{game.getId(), game.getCurrentActivity().getId()});
            game.getPlayers().parallelStream().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJsonResponse().toString()));
        }
    }

    private JsonObject createJsonResponse(){
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.CHANGE_ACTIVITY.key);
        response.addProperty(GameResponseKey.COST_PROJECT.key,game.getProject().getCost());
        response.addProperty(GameResponseKey.DELAY_PROJECT.key,game.getProject().getDays());
        response.addProperty(GameResponseKey.FAILURE_PROJECT.key,game.getProject().getRisks());
        response.addProperty(GameResponseKey.ACTIVITY_ID.key,game.getCurrentActivity().getId());
        JsonArray extraPaymentArray = new JsonArray();
        for (PayResources payResources : game.getCurrentActivity().getPayResourcesList()) {
            if (payResources.isExtraPayment()){
                JsonObject extraPayment = new JsonObject();
                for (Integer amount : payResources.getPriceAndBonusMap().keySet()) {
                    extraPayment.addProperty(GameResponseKey.ROLE_ID.key, payResources.getRoleID());
                    extraPayment.addProperty(GameResponseKey.AMOUNT.key, amount);
                }

                extraPaymentArray.add(extraPayment);
            }
        }
        response.add(GameResponseKey.EXTRA_PAYING.key, extraPaymentArray);
        return response;
    }

    private void sendErrorToAllPlayers(String errorMessage){
        game.getPlayers().parallelStream().forEach(player -> new Messenger(player.getSession()).sendError(errorMessage));
    }
}
