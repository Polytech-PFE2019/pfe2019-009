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
    private Player giver;
    private Player receiver;

    public PayContractEvent(Game game, Player giver, Player receiver) {
        this.game = game;
        this.giver = giver;
        this.receiver = receiver;

    }
    @Override
    public void processEvent() {

        Activity activity = game.getCurrentActivity();

        //Optional<PayPlayer> payPlayerOptional = activity.getPayPlayerList().stream().filter(payPlayer -> payPlayer.getNegotiation().getGiverRoleID() == giver.getID()&& payPlayer.getNegotiation().getReceiverRoleID() == receiver.getID() ).findAny();;
        Optional<PayContract> payPlayerOptional = Optional.empty();
        if(!payPlayerOptional.isPresent()){
            //todo throw some error
           return;
        }

        PayContract payContract = payPlayerOptional.get();
        Negotiation negociation = payContract.getNegotiation();
        int percentage = payContract.getPercentage();
        negociation.pay(percentage);
        int amountPaid = negociation.getLastPayment();
        giver.subtractMoney(amountPaid);
        receiver.addMoney(amountPaid);
        payContract.setPaid();

        new Messenger(giver.getSession()).sendSpecificMessageToAUser(createResponseToUser(giver,amountPaid));
        new Messenger(receiver.getSession()).sendSpecificMessageToAUser(createResponseToUser(receiver, amountPaid));


    }

    private String createResponseToUser(Player player, int amountPaid) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "PAY_PLAYER");
        response.addProperty(GameResponseKey.GIVERID.key, giver.getID());
        response.addProperty(GameResponseKey.RECEIVERID.key, receiver.getID());
        response.addProperty(GameResponseKey.AMOUNT.key,amountPaid);
        response.addProperty(GameResponseKey.MONEY.key, player.getMoney());

        return response.toString();
    }
}
