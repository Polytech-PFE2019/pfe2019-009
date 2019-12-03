package org.polytech.pfe.domego.protocol.game.negociation;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.negotiation.NegotiationActivity;
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

    public NegotiationEvent(WebSocketSession session, Map<String,String> request){
        this.messenger = new Messenger(session);
        this.gameAccessor = new GameAccessor();
        this.request = request;
    }

    protected void processRequest(){
        Optional<Game> optionalGame = this.gameAccessor.getGameById(request.get(GameRequestKey.GAMEID.getKey()));
        if(!optionalGame.isPresent()){
            this.messenger.sendError("GAME NOT FOUND");
            return;
        }

        this.game = optionalGame.get();
        String negotiationID = request.get(GameRequestKey.NEGOTIATIONID.getKey());

        NegotiationActivity activity = (NegotiationActivity) game.getCurrentActivity();

        Optional<Negotiation> negotiationOptional = activity.getNegotiationByID(negotiationID);
        if(negotiationOptional.isEmpty()){
            this.messenger.sendError("NEGOCIATION NOT FOUND");
            return;
        }

        this.negotiation = negotiationOptional.get();

        Optional<Player> optionalGiver = game.getPlayerByRoleID(negotiation.getGiverRoleID());

        if (!optionalGiver.isPresent()){
            this.messenger.sendError("GIVER NOT FOUND");
            return;
        }

        Optional<Player> optionalReceiver = game.getPlayerByRoleID(negotiation.getReceiverRoleID());

        if (!optionalReceiver.isPresent()){
            this.messenger.sendError("RECEIVER NOT FOUND");
            return;
        }

        this.giver = optionalGiver.get();
        this.receiver = optionalReceiver.get();
    }

    protected void sendResponses(String response){

        if(giver.getSession() == messenger.getSession()){
            otherPlayerMessenger = new Messenger(receiver.getSession());
        }
        else {
            otherPlayerMessenger = new Messenger(giver.getSession());
        }

        messenger.sendSpecificMessageToAUser(response);
        otherPlayerMessenger.sendSpecificMessageToAUser(response);;
    }

}
