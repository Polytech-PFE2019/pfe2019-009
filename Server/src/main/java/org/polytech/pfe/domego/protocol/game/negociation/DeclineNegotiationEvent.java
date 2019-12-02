package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Negociation;
import org.polytech.pfe.domego.models.activity.NegociationActivity;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.RequestArgumentKey;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class DeclineNegotiationEvent implements EventProtocol {


    private Logger logger = Logger.getGlobal();
    private Map<String,String> request;
    private Messenger messenger;
    private GameAccessor gameAccessor;

    public DeclineNegotiationEvent(WebSocketSession session, Map request) {
        this.messenger = new Messenger(session);
        this.request = request;
        this.gameAccessor = new GameAccessor();

    }

    @Override
    public void processEvent() {
        try {
            this.checkArgumentOfRequest();
        }catch (MissArgumentToRequestException missArgumentToRequest){
            this.messenger.sendErrorCuzMissingArgument(missArgumentToRequest.getMissKey().getKey());
            return;
        }

        Optional<Game> optionalGame = this.gameAccessor.getGameById(request.get(GameRequestKey.GAMEID.getKey()));
        if(optionalGame.isEmpty()){
            this.messenger.sendError("GAME NOT FOUND");
            return;
        }

        Game game = optionalGame.get();

        String negotiationID = request.get(GameRequestKey.NEGOTIATIONID.getKey());

        NegociationActivity activity = (NegociationActivity) game.getCurrentActivity();

        Optional<Negociation> negotiationOptional = activity.getNegotiationByID(negotiationID);
        if(negotiationOptional.isEmpty()){
            this.messenger.sendError("NEGOCIATION NOT FOUND");
            return;
        }
        Negociation negotiation = negotiationOptional.get();

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

        Player giver = optionalGiver.get();
        Player receiver = optionalReceiver.get();

        sendResponseToUsers(giver,receiver,negotiation);


    }

    private void sendResponseToUsers(Player giver, Player receiver, Negociation negociation) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "DECLINE_NEGOTIATE");
        response.addProperty(GameResponseKey.USERID.key,request.get(GameRequestKey.USERID.getKey()));
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negociation.getId());
        response.addProperty(GameResponseKey.DECLINETYPE.key, request.get(GameRequestKey.DECLINETYPE.getKey()));

        Messenger otherPlayerMessenger;

        if(giver.getSession() == messenger.getSession()){
            otherPlayerMessenger = new Messenger(receiver.getSession());
        }
        else {
            otherPlayerMessenger = new Messenger(giver.getSession());
        }

        messenger.sendSpecificMessageToAUser(response.toString());
        otherPlayerMessenger.sendSpecificMessageToAUser(response.toString());


    }

    private void checkArgumentOfRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.NEGOTIATIONID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.NEGOTIATIONID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.USERID);
        if(!request.containsKey(GameRequestKey.DECLINETYPE.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.DECLINETYPE);
    }
}
