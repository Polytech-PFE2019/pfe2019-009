package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.PayResourceType;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;


public class PayResourcesEvent implements EventProtocol {

    private Map<String,String> request;
    private GameInstance gameInstance;
    private Messenger messenger;

    public PayResourcesEvent(WebSocketSession session, Map request) {
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
        }

        Optional<Game> optionalGame = gameInstance.getSpecificGameByID(request.get(GameRequestKey.GAMEID.getKey()));
        if(!optionalGame.isPresent()){
            this.messenger.sendError("GAME NOT FOUND");
            return;
        }

        Game game = optionalGame.get();


        Optional<Player> optionalPlayer = game.getPlayers().stream().filter(player -> player.getID().equals(request.get(GameRequestKey.USERID.getKey()))).findAny();

        if (!optionalPlayer.isPresent()){
            this.messenger.sendError("USER NOT FOUND");
            return;
        }

        Player player = optionalPlayer.get();

        this.payResources(game,player);

    }


    private void payResources(Game game, Player player){
        Activity activity = game.getCurrentActivity();

        int roleID = player.getRole().getId();
        int numberOfResource = Integer.parseInt(request.get(GameRequestKey.AMOUNT.getKey()));

        PayResourceType payResourceType = PayResourceType.valueOf(request.get(GameRequestKey.TYPE.getKey()));

        if (numberOfResource > player.getResourcesAmount()) {
            this.messenger.sendError("USER HAS NOT ENOUGH RESOURCES");
            return;
        }
        if(!activity.payResources(roleID,payResourceType, numberOfResource)){
            this.messenger.sendError("NOT ENOUGH RESOURCES TO PAY");
            return;

        }
        player.substractResources(numberOfResource);
        this.sendResponseToUser(player);

        new UpdateGameEvent(game).processEvent();
    }

    private void sendResponseToUser(Player player) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "PAY_RESOURCES");
        response.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        response.addProperty(GameResponseKey.BONUSTYPE.key, request.get(GameRequestKey.TYPE.getKey()));
        messenger.sendSpecificMessageToAUser(response.toString());
    }


    private void checkArgumentOfRequest() throws MissArgumentToRequestException{
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.USERID);
        if(!request.containsKey(GameRequestKey.AMOUNT.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.AMOUNT);
        if(!request.containsKey(GameRequestKey.TYPE.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.AMOUNT);
    }
}
