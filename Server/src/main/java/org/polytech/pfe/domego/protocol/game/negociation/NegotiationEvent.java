package org.polytech.pfe.domego.protocol.game.negociation;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.negotiation.NegotiationStatus;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;

public abstract class NegotiationEvent {
    protected Game game;
    protected Negotiation negotiation;
    protected Player giver;
    protected Player receiver;
    protected Messenger messenger;
    protected Map<String,String> request;
    protected Messenger otherPlayerMessenger;
    private GameAccessor gameAccessor;
    protected WebSocketSession session;

    public NegotiationEvent(WebSocketSession session, Map<String,String> request){
        this.session = session;
        this.messenger = new Messenger(session);
        this.gameAccessor = new GameAccessor();
        this.request = request;
    }

    protected boolean processRequest(){
        Optional<Game> optionalGame = this.gameAccessor.getGameById(request.get(GameRequestKey.GAMEID.getKey()));
        if(!optionalGame.isPresent()){
            this.messenger.sendError("GAME NOT FOUND");
            return false;
        }

        this.game = optionalGame.get();
        String negotiationID = request.get(GameRequestKey.NEGOTIATIONID.getKey());

        Activity activity = game.getCurrentActivity();

        Optional<Negotiation> negotiationOptional = activity.getNegotiationList().stream().filter(negotiation -> negotiation.getId().equals(negotiationID)).findAny();
        if(negotiationOptional.isEmpty()){
            this.messenger.sendError("NEGOTIATION NOT FOUND");
            return false;
        }

        this.negotiation = negotiationOptional.get();

        if(this.negotiation.getNegotiationStatus() == NegotiationStatus.SUCCESS || this.negotiation.getNegotiationStatus() == NegotiationStatus.FAILURE ){
            this.messenger.sendError("NEGOTIATION ALREADY FINISHED");
            return false;
        }

        Optional<Player> optionalGiver = game.getPlayerByRoleID(negotiation.getGiverRoleID());

        if (optionalGiver.isEmpty()){
            this.messenger.sendError("GIVER NOT FOUND");
            return false;
        }

        Optional<Player> optionalReceiver = game.getPlayerByRoleID(negotiation.getReceiverRoleID());

        if (optionalReceiver.isEmpty()){
            this.messenger.sendError("RECEIVER NOT FOUND");
            return false;
        }

        this.giver = optionalGiver.get();
        this.receiver = optionalReceiver.get();

        return true;
    }

    protected void sendResponses(String response){

        if(giver.getSession() == session){
            otherPlayerMessenger = new Messenger(receiver.getSession());
        }
        else {
            otherPlayerMessenger = new Messenger(giver.getSession());
        }

        messenger.sendSpecificMessageToAUser(response);
        otherPlayerMessenger.sendSpecificMessageToAUser(response);;
    }

}
