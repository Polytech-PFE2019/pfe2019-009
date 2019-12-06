package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.logging.Logger;

public class DeclineNegotiationEvent extends NegotiationEvent implements EventProtocol {


    private Logger logger = Logger.getGlobal();

    public DeclineNegotiationEvent(WebSocketSession session, Map<String,String> request) {
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
        response.addProperty(GameResponseKey.RESPONSE.key, "DECLINE_NEGOTIATE");
        response.addProperty(GameResponseKey.USER_ID.key,request.get(GameRequestKey.USERID.getKey()));
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negotiation.getId());

       super.sendResponses(response.toString());


    }

    private void checkArgumentOfRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.NEGOTIATIONID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.NEGOTIATIONID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.USERID);
    }
}
