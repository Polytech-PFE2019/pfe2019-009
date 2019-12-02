package org.polytech.pfe.domego.protocol.game.negociation;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Negociation;
import org.polytech.pfe.domego.models.activity.NegociationActivity;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class StartNegotiationEvent implements EventProtocol {


    private Logger logger = Logger.getGlobal();
    private Map<String,String> request;
    private GameInstance gameInstance;
    private Messenger messenger;

    public StartNegotiationEvent(WebSocketSession session, Map request) {
        this.messenger = new Messenger(session);
        this.request = request;
        gameInstance = GameInstance.getInstance();

    }

    @Override
    public void processEvent() {
        try {
            this.checkArgumentOfRequest();
        }catch (MissArgumentToRequestException missArgumentToRequest){
            this.messenger.sendErrorCuzMissingArgument(missArgumentToRequest.getMissKey().getKey());
            return;
        }

        Optional<Game> optionalGame = gameInstance.getSpecificGameByID(request.get(GameRequestKey.GAMEID.getKey()));
        if(optionalGame.isEmpty()){
            this.messenger.sendError("GAME NOT FOUND");
            return;
        }

        Game game = optionalGame.get();
        Optional<Player> optionalPlayer = game.getPlayerById(request.get(GameRequestKey.GIVERID.getKey()));

        if (optionalPlayer.isEmpty()){
            this.messenger.sendError("GIVER NOT FOUND");
            return;
        }

        Optional<Player> optionalPlayer2 = game.getPlayerById(request.get(GameRequestKey.RECEIVERID.getKey()));

        if (optionalPlayer2.isEmpty()){
            this.messenger.sendError("RECEIVER NOT FOUND");
            return;
        }

        Player giver = optionalPlayer.get();
        Player receiver = optionalPlayer2.get();

        int giverID = giver.getRole().getId();
        int receiverID = receiver.getRole().getId();
        NegociationActivity activity = (NegociationActivity) game.getCurrentActivity();

        Optional<Negociation> negociationOptional = activity.getNegocationByRoleIDs(giverID,receiverID);
        if(negociationOptional.isEmpty()){
            this.messenger.sendError("NEGOCIATION NOT FOUND");
            return;
        }

        Negociation negociation = negociationOptional.get();
        sendResponseToUser(giver,receiver,negociation);
        sendResponseToUser(giver,receiver,negociation);


    }

    private void sendResponseToUser(Player giver, Player receiver, Negociation negociation) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "START_NEGOCIATE");
        response.addProperty(GameResponseKey.GIVERID.key,giver.getID());
        response.addProperty(GameResponseKey.RECEIVERID.key, receiver.getID());
        response.addProperty(GameResponseKey.NEGOCIATIONID.key, negociation.getId());
        messenger.sendSpecificMessageToAUser(response.toString());
    }

    private void checkArgumentOfRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.GIVERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GIVERID);
        if(!request.containsKey(GameRequestKey.RECEIVERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.RECEIVERID);
    }
}
