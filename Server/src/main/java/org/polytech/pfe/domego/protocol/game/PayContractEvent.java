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
import java.util.logging.Level;
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

            double amountPaid =  negotiation.pay(percentage);
            Optional<Player> optionalGiver = game.getPlayerByRoleID(payContract.getNegotiation().getGiverRoleID());
            if (optionalGiver.isEmpty())
                return;

            Player giver = optionalGiver.get();
            Optional<Player> optionalReceiver = game.getPlayerByRoleID(payContract.getNegotiation().getReceiverRoleID());
            if (optionalReceiver.isEmpty())
                return;



            Player receiver = optionalReceiver.get();
            logger.log(Level.INFO,
                    "PayContractEvent : GIVER name :  {0}, money {1} resource {2}",
                    new Object[]{giver.getName(), giver.getMoney(), giver.getResourcesAmount()});
            logger.log(Level.INFO,
                    "PayContractEvent : RECEIVER name :  {0}, money {1} resource {2}",
                    new Object[]{receiver.getName(), receiver.getMoney(), receiver.getResourcesAmount()});

            giver.subtractMoney(amountPaid);
            receiver.addMoney(amountPaid);
            payContract.setPaid();
            new Messenger(giver.getSession()).sendSpecificMessageToAUser(createResponseToUser(giver, giver, receiver,amountPaid, negotiation.getId()));
            new Messenger(receiver.getSession()).sendSpecificMessageToAUser(createResponseToUser(receiver, giver, receiver, amountPaid, negotiation.getId()));

            logger.log(Level.INFO,
                    "PayContractEvent : In game {0}, {1} paid {2} the amount {3}.",
                    new Object[]{game.getId(), giver.getRole().getName(), receiver.getRole().getName() ,  amountPaid});

            logger.log(Level.INFO,
                    "PayContractEvent : GIVER name :  {0}, money {1} resource {2}",
                    new Object[]{giver.getName(), giver.getMoney(), giver.getResourcesAmount()});
            logger.log(Level.INFO,
                    "PayContractEvent : RECEIVER name :  {0}, money {1} resource {2}",
                    new Object[]{receiver.getName(), receiver.getMoney(), receiver.getResourcesAmount()});
        }
    }

    private String createResponseToUser(Player player, Player giver, Player receiver, double amountPaid, String negotiationID) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "PAY_CONTRACT");
        response.addProperty(GameResponseKey.GIVER_ROLE_NAME.key, giver.getRole().getName().getName());
        response.addProperty(GameResponseKey.RECEIVER_ROLE_NAME.key, receiver.getRole().getName().getId());
        response.addProperty(GameResponseKey.GIVERID.key, giver.getRole().getId());
        response.addProperty(GameResponseKey.RECEIVERID.key, receiver.getRole().getId());
        response.addProperty(GameResponseKey.AMOUNT.key,amountPaid);
        response.addProperty(GameResponseKey.MONEY.key, player.getMoney());
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negotiationID);

        return response.toString();
    }
}
