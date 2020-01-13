package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.calculator.InfoProjectGameCalculator;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

public class UpdatePaymentGameEvent implements EventProtocol {

    private Player player;
    private Game game;
    private InfoProjectGameCalculator projectGameCalculator;
    private int minTime;
    private int maxTime;
    private int minFailure;
    private int maxFailure;


    public UpdatePaymentGameEvent(Game game, Player player) {
        this.player = player;
        this.game = game;
        this.projectGameCalculator = new InfoProjectGameCalculator(game);
        minTime = projectGameCalculator.getMinTimeOfProject();
        maxTime = projectGameCalculator.getMaxTimeOfProject();
        minFailure = projectGameCalculator.getMinFailureOfProject();
        maxFailure = projectGameCalculator.getMaxFailureOfProject();
    }

    @Override
    public void processEvent() {
        this.game.getPlayers().stream().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJSONResponse(player).toString()));

    }


    private JsonObject createJSONResponse(Player player){
        JsonObject updatePayment = new JsonObject();
        updatePayment.addProperty(GameResponseKey.RESPONSE.key, GameResponseKey.UPDATE_PAYMENT.key);
        updatePayment.addProperty(GameResponseKey.ACTIVITY_ID.key,game.getCurrentActivity().getId());
        updatePayment.addProperty(GameResponseKey.ROLE_ID.key,player.getRole().getId());
        updatePayment.add(GameResponseKey.PROJECT.key, createProjectObject(player));
        JsonArray paymentsJSON = new JsonArray();
        for (PayResources payment: game.getCurrentActivity().getPayResourcesList()) {
            JsonObject paymentJSon = new JsonObject();
            paymentJSon.addProperty(GameResponseKey.ROLE_ID.key, payment.getRoleID());
            paymentJSon.addProperty(GameResponseKey.AMOUNT.key, payment.getAmountPaid());
            paymentJSon.addProperty(GameResponseKey.TYPE.key, payment.getPayResourceType().getName());
            paymentJSon.addProperty(GameResponseKey.BONUS.key, payment.getBonusGiven());
            paymentJSon.addProperty(GameResponseKey.EXTRA_PAYING.key, payment.isExtraPayment());
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
        projectJson.addProperty(GameResponseKey.MAX_FAILURE.key, maxFailure);

        return projectJson;
    }
}
