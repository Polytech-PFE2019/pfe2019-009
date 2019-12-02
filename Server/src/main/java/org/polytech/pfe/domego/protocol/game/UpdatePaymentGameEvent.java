package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.List;

public class UpdatePaymentGameEvent implements EventProtocol {

    private Activity activity;
    private List<Player> players;
    private List<Payment> payments;
    private int roleId;

    public UpdatePaymentGameEvent(Activity activity, List<Player> players, List<Payment> payments, int roleID) {
        this.activity = activity;
        this.players = players;
        this.payments = payments;
        this.roleId = roleID;
    }

    @Override
    public void processEvent() {
        players.forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJSONResponse().toString()));

    }


    private JsonObject createJSONResponse(){
        JsonObject updatePayement = new JsonObject();
        updatePayement.addProperty(GameResponseKey.RESPONSE.key, GameResponseKey.UPDATE_PAYMENT.key);
        updatePayement.addProperty(GameResponseKey.ACTIVITY_ID.key,activity.getId());
        updatePayement.addProperty(GameResponseKey.ROLE_ID.key,roleId);
        JsonArray paymentsJSON = new JsonArray();
        for (Payment payment: payments) {
            JsonObject paymentJSon = new JsonObject();
            paymentJSon.addProperty(GameResponseKey.AMOUNT.key, payment.getAmount());
            paymentJSon.addProperty(GameResponseKey.TYPE.key, payment.getType().getId());
            paymentsJSON.add(paymentJSon);
        }
        updatePayement.add(GameResponseKey.PAYMENTS.key, paymentsJSON);

        return updatePayement;


    }
}
