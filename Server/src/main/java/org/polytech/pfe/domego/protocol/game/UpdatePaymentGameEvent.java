package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.calculator.InfoProjectGameCalculator;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.PayResources;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.List;

public class UpdatePaymentGameEvent implements EventProtocol {

    private Activity activity;
    private List<Player> players;
    private List<PayResources> payments;
    private int roleId;
    private Game game;
    private InfoProjectGameCalculator projectGameCalculator;
    private int minTime;
    private int maxTime;
    private int minFailure;
    private int maxFailure;


    public UpdatePaymentGameEvent(Game game, Activity activity, List<Player> players, List<PayResources> payments, int roleID) {
        this.activity = activity;
        this.game = game;
        this.players = players;
        this.payments = payments;
        this.roleId = roleID;
        this.projectGameCalculator = new InfoProjectGameCalculator(game);
        minTime = projectGameCalculator.getMinTimeOfProject();
        maxTime = projectGameCalculator.getMaxTimeOfProject();
        minFailure = projectGameCalculator.getMinFailureOfProject();
        maxFailure = projectGameCalculator.getMaxFailureOfProject();
    }

    @Override
    public void processEvent() {
        this.game.getPlayers().parallelStream().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJSONResponse(player).toString()));

    }


    private JsonObject createJSONResponse(Player player){
        JsonObject updatePayment = new JsonObject();
        updatePayment.addProperty(GameResponseKey.RESPONSE.key, GameResponseKey.UPDATE_PAYMENT.key);
        updatePayment.addProperty(GameResponseKey.ACTIVITY_ID.key,activity.getId());
        updatePayment.addProperty(GameResponseKey.ROLE_ID.key,roleId);
        updatePayment.add(GameResponseKey.PROJECT.key, createProjectObject(player));
        JsonArray paymentsJSON = new JsonArray();
        for (PayResources payment: payments) {
            JsonObject paymentJSon = new JsonObject();
            paymentJSon.addProperty(GameResponseKey.AMOUNT.key, payment.getAmountPaid());
            paymentJSon.addProperty(GameResponseKey.TYPE.key, payment.getPayResourceType().getName());
            paymentJSon.addProperty(GameResponseKey.BONUS.key, payment.getBonusGiven());
            paymentsJSON.add(paymentJSon);
        }
        updatePayment.add(GameResponseKey.PAYMENTS.key, paymentsJSON);

        return updatePayment;


    }

    private JsonObject createProjectObject(Player player){
        JsonObject projectJson = new JsonObject();

        projectJson.addProperty(GameResponseKey.MIN_COST.key, projectGameCalculator.getMinResourcesToPay(player.getRole().getName()));
        projectJson.addProperty(GameResponseKey.MAX_COST.key, projectGameCalculator.getMaxResourcesToPay(player.getRole().getName()));
        projectJson.addProperty(GameResponseKey.MIN_TIME.key, minTime);
        projectJson.addProperty(GameResponseKey.MAX_TIME.key, maxTime);
        projectJson.addProperty(GameResponseKey.MIN_FAILURE.key, minFailure);
        projectJson.addProperty(GameResponseKey.MAX_TIME.key, maxTime);

        return projectJson;
    }
}
