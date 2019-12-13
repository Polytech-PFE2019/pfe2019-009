package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.activity.negotiation.NegotiationStatus;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class EstablishNegotiationEvent extends NegotiationEvent implements EventProtocol {


    private Logger logger = Logger.getGlobal();

    public EstablishNegotiationEvent(WebSocketSession session, Map<String,String> request) {
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

        launchNegotiation();

    }

    private void sendResponseToUsers() {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "ESTABLISH_NEGOTIATE");
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negotiation.getId());

        super.sendResponses(response.toString());
    }

    private void checkArgumentOfRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.NEGOTIATIONID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.NEGOTIATIONID);
    }

    private void launchNegotiation(){

        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        Runnable test = this::endTimer;
        executor.schedule(
                test, negotiation.getTime(), SECONDS);

        executor.shutdown();
    }

    private void endTimer(){
        if(!negotiation.getNegotiationStatus().equals(NegotiationStatus.SUCCESS)){
            new FailureNegotiationEvent(game,negotiation,giver,receiver).processEvent();
        }

    }

}
