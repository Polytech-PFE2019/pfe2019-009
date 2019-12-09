package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.pay.PayContract;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.Optional;
import java.util.logging.Logger;

public class PayContractEvent implements EventProtocol {

    private Logger logger = Logger.getGlobal();
    private Game game;

    public PayContractEvent(Game game) {
        this.game = game;

    }
    @Override
    public void processEvent() {

        Activity activity = game.getCurrentActivity();

        if(activity.getPayContractList().isEmpty())
            return;

        for (PayContract payContract : activity.getPayContractList()) {

            Negotiation negotiation = payContract.getNegotiation();
            int percentage = payContract.getPercentage();
            negotiation.pay(percentage);
            int amountPaid = negotiation.getLastPayment();
            Optional<Player> optionalGiver = game.getPlayerByRoleID(payContract.getNegotiation().getGiverRoleID());
            if (optionalGiver.isEmpty())
                return;

            Player giver = optionalGiver.get();
            Optional<Player> optionalReceiver = game.getPlayerByRoleID(payContract.getNegotiation().getReceiverRoleID());
            if (optionalReceiver.isEmpty())
                return;

            Player receiver = optionalGiver.get();
            giver.subtractMoney(amountPaid);
            receiver.addMoney(amountPaid);
            System.out.println("GIVER get MONEY : " + giver.getMoney());
            System.out.println("RECEIVER get MONEY : " + receiver.getMoney());
            payContract.setPaid();
            new Messenger(giver.getSession()).sendSpecificMessageToAUser(createResponseToUser(giver, giver, receiver,amountPaid));
            new Messenger(receiver.getSession()).sendSpecificMessageToAUser(createResponseToUser(receiver, giver, receiver, amountPaid));
        }


    }

    private String createResponseToUser(Player player, Player giver, Player receiver, int amountPaid) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "PAY_PLAYER");
        response.addProperty(GameResponseKey.GIVERID.key, giver.getID());
        response.addProperty(GameResponseKey.RECEIVERID.key, receiver.getID());
        response.addProperty(GameResponseKey.AMOUNT.key,amountPaid);
        response.addProperty(GameResponseKey.MONEY.key, player.getMoney());

        return response.toString();
    }
}
