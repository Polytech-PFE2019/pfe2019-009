package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.DrawCardEvent;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EndNegotiationEvent extends NegotiationEvent implements EventProtocol {

    private Logger logger = Logger.getGlobal();

    public EndNegotiationEvent(WebSocketSession session, Map<String,String> request) {
        super(session,request);
    }
    @Override
    public void processEvent() {
        try {
            this.checkArgumentOfRequest();
        }catch (MissArgumentToRequestException missArgumentToRequest){
            this.messenger.sendErrorCuzMissingArgument(missArgumentToRequest.getMissKey().getKey());
            return;
        }

        if(!super.processRequest()){
            return;
        }

        int amount = Integer.parseInt(request.get(GameRequestKey.AMOUNT.getKey()));

        negotiation.negotiate(amount);

        logger.log(Level.INFO,
                "EndNegotiationEvent : In game {0} the negotiation beetween {1} and {2} is over. The amount of the negociation is {3}.",
                new Object[]{game.getId(), giver.getRole().getName(), receiver.getRole().getName() ,  negotiation.getAmountNegotiated()});

        sendResponseToUsers(negotiation);

        Activity currentActivity = game.getCurrentActivity();
        if (currentActivity.isActivityDone()) {
            if (!currentActivity.getActivityStatus().equals(ActivityStatus.DONE)) {
                currentActivity.doneActivity();
                new DrawCardEvent(game).processEvent();
            }
        }


    }
    private void sendResponseToUsers(Negotiation negociation) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "END_NEGOTIATE");
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negociation.getId());
        response.addProperty(GameResponseKey.AMOUNT.key, negociation.getAmountNegotiated());
        response.addProperty(GameResponseKey.GIVERID.key, negociation.getGiverRoleID());
        response.addProperty(GameResponseKey.RECEIVERID.key, negociation.getReceiverRoleID());

        super.sendResponses(response.toString());

    }

    private void checkArgumentOfRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.NEGOTIATIONID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.NEGOTIATIONID);
        if(!request.containsKey(GameRequestKey.AMOUNT.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.AMOUNT);

    }
}
