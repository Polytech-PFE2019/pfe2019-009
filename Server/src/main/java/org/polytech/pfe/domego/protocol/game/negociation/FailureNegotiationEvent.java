package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.negotiation.Negociation;
import org.polytech.pfe.domego.models.activity.negotiation.NegotiationStatus;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FailureNegotiationEvent implements EventProtocol {
    private Game game;
    private Negociation negotiation;
    private Player giver;
    private Player receiver;
    private Messenger messenger;
    private Messenger messenger2;
    private Logger logger = Logger.getGlobal();


    public FailureNegotiationEvent(Game game, Negociation negotiation, Messenger messenger, Messenger messenger2, Player giver, Player receiver){
        this.game = game;
        this.negotiation = negotiation;
        this.messenger = messenger;
        this.messenger2 = messenger2;
        this.receiver = receiver;
        this.giver = giver;
    }


    @Override
    public void processEvent() {
            negotiation.fail();
            sendFailureResponseToUsers();
            logger.log(Level.INFO,
                    "BuyResourceEvent : In game {0} the negotiation beetween {1} and {2} failed. The amount of the contract drew randomly is {3}.",
                    new Object[]{game.getId(), giver.getRole().getName(), receiver.getRole().getName() ,  negotiation.getAmountNegociated()});
    }

    private void sendFailureResponseToUsers() {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "FAIL_NEGOTIATE");
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negotiation.getId());
        response.addProperty(GameResponseKey.AMOUNT.key, negotiation.getAmountNegociated());
        messenger.sendSpecificMessageToAUser(response.toString());
        messenger2.sendSpecificMessageToAUser(response.toString());
    }
}
