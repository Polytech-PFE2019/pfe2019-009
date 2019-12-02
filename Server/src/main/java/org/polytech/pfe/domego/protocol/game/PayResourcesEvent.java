package org.polytech.pfe.domego.protocol.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class PayResourcesEvent implements EventProtocol {

    private Map<String, ?> request;
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
            return;
        }

        Optional<Game> optionalGame = gameInstance.getSpecificGameByID(String.valueOf(request.get(GameRequestKey.GAMEID.getKey())));
        if(!optionalGame.isPresent()){
            this.messenger.sendError("GAME NOT FOUND");
            return;
        }

        Game game = optionalGame.get();


        Optional<Player> optionalPlayer = game.getPlayers().stream().filter(player -> player.getID().equals(String.valueOf(request.get(GameRequestKey.USERID.getKey())))).findAny();

        if (!optionalPlayer.isPresent()){
            this.messenger.sendError("USER NOT FOUND");
            return;
        }

        Player player = optionalPlayer.get();

        this.payResources(game,player);

    }


    private void payResources(Game game, Player player){
        Type founderListType = new TypeToken<ArrayList<Payment>>(){}.getType();
        List<Payment> payments = new Gson().fromJson(request.get("payments").toString(), founderListType);
        game.payForCurrentActivity(player, payments);
        this.sendResponseToUser(game.getPlayerById(player.getID()).get());


        //new UpdateGameEvent(game).processEvent();
    }

    private void sendResponseToUser(Player player) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "PAY_RESOURCES");
        response.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        //response.addProperty(GameResponseKey.BONUSTYPE.key, request.get(GameRequestKey.TYPE.getKey()));
        messenger.sendSpecificMessageToAUser(response.toString());
    }


    private void checkArgumentOfRequest() throws MissArgumentToRequestException{
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.USERID);
        if(!request.containsKey(GameRequestKey.PAYMENTS.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.PAYMENTS);
    }
}


