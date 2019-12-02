package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.Negociation;
import org.polytech.pfe.domego.models.activity.PayPlayer;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.Optional;
import java.util.logging.Logger;

public class PayPlayerEvent implements EventProtocol {

    private Logger logger = Logger.getGlobal();
    private Game game;
    private Player giver;
    private Player receiver;

    public PayPlayerEvent(Game game, Player giver, Player receiver) {
        this.game = game;
        this.giver = giver;
        this.receiver = receiver;

    }
    @Override
    public void processEvent() {

        Activity activity = game.getCurrentActivity();

        Optional<PayPlayer> payPlayerOptional = activity.getPayPlayerByRoleIDs(giver.getRole().getId(), receiver.getRole().getId());

        if(!payPlayerOptional.isPresent()){
            //todo throw some error
           return;
        }

        PayPlayer payPlayer = payPlayerOptional.get();
        Negociation negociation = payPlayer.getNegociation();
        int percentage = payPlayer.getPercentage();
        negociation.pay(percentage);
        int amountPaid = negociation.getLastPayment();
        giver.subtractMoney(amountPaid);
        receiver.addMoney(amountPaid);
        payPlayer.setPaid();

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
