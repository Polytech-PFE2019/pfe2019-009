package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.DrawCardEvent;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FailureNegotiationEvent implements EventProtocol {
    private Game game;
    private Negotiation negotiation;
    private Player giver;
    private Player receiver;
    private Logger logger = Logger.getGlobal();


    public FailureNegotiationEvent(Game game, Negotiation negotiation, Player giver, Player receiver){
        this.game = game;
        this.negotiation = negotiation;
        this.receiver = receiver;
        this.giver = giver;
    }


    @Override
    public void processEvent() {
            negotiation.fail();
            sendFailureResponseToUsers();
            logger.log(Level.INFO,
                    "FailureNegociationEvent : In game {0} the negotiation beetween {1} and {2} failed. The amount of the contract drew randomly is {3}.",
                    new Object[]{game.getId(), giver.getRole().getName(), receiver.getRole().getName() ,  negotiation.getAmountNegotiated()});

            Activity currentActivity = game.getCurrentActivity();
            if (currentActivity.isActivityDone()) {
                if (!currentActivity.getActivityStatus().equals(ActivityStatus.DONE)) {
                    currentActivity.doneActivity();
                    new DrawCardEvent(game).processEvent();
                }
            }
    }

    private void sendFailureResponseToUsers() {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "FAIL_NEGOTIATE");
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negotiation.getId());
        response.addProperty(GameResponseKey.AMOUNT.key, negotiation.getAmountNegotiated());
        response.addProperty(GameResponseKey.GIVERID.key, negotiation.getGiverRoleID());
        response.addProperty(GameResponseKey.RECEIVERID.key, negotiation.getReceiverRoleID());
        new Messenger(giver.getSession()).sendSpecificMessageToAUser(response.toString());
        new Messenger(receiver.getSession()).sendSpecificMessageToAUser(response.toString());
    }
}
