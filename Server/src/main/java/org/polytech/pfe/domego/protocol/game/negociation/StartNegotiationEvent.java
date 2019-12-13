package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.logging.Logger;

public class StartNegotiationEvent extends NegotiationEvent implements EventProtocol {


    private Logger logger = Logger.getGlobal();

    public StartNegotiationEvent(WebSocketSession session, Map<String,String> request) {
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

        sendResponseToUsers();
    }

    private void sendResponseToUsers() {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "START_NEGOTIATE");
        response.addProperty(GameResponseKey.TIME.key, negotiation.getTime());

        response.addProperty(GameResponseKey.GIVERID.key, giver.getRole().getId());
        response.addProperty(GameResponseKey.RECEIVERID.key, receiver.getRole().getId());
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negotiation.getId());
        response.addProperty(GameResponseKey.OTHER_USER_NAME.key, receiver.getRole().getName().toString());

        messenger.sendSpecificMessageToAUser(response.toString());

        response.addProperty(GameResponseKey.OTHER_USER_NAME.key, giver.getRole().getName().toString());

        otherPlayerMessenger = new Messenger(receiver.getSession());
        otherPlayerMessenger.sendSpecificMessageToAUser(response.toString());

    }

    private void checkArgumentOfRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.NEGOTIATIONID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.NEGOTIATIONID);
    }

}
