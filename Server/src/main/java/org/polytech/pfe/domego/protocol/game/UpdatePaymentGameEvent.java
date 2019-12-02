package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Messenger;
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

    public UpdatePaymentGameEvent(Activity activity, List<Player> players, List<PayResources> payments, int roleID) {
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
        for (PayResources payment: payments) {
            JsonObject paymentJSon = new JsonObject();
            paymentJSon.addProperty(GameResponseKey.AMOUNT.key, payment.getAmountPaid());
            paymentJSon.addProperty(GameResponseKey.TYPE.key, payment.getPayResourceType().getName());
            paymentJSon.addProperty(GameResponseKey.BONUS.key, payment.getBonusGiven());
            paymentsJSON.add(paymentJSon);
        }
        updatePayement.add(GameResponseKey.PAYMENTS.key, paymentsJSON);

        return updatePayement;


    }
}
