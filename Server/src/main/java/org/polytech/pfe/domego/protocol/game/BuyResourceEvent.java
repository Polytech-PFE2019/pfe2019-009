package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.buisness.Game;
import org.polytech.pfe.domego.components.buisness.Messenger;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequest;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;


public class BuyResourceEvent implements EventProtocol {

    private Map<String,String> request;
    private GameInstance gameInstance;
    private Messenger messenger;

    public BuyResourceEvent(WebSocketSession session, Map<String,String> request) {
        this.messenger = new Messenger(session);
        this.request = request;
        gameInstance = GameInstance.getInstance();

    }

    @Override
    public void processEvent() {
        try {
            this.checkArgumentOfRequest();
        }catch (MissArgumentToRequest missArgumentToRequest){
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
        this.buyResource(game,player);

    }


    private void buyResource(Game game, Player player){
        int numberOfResource = Integer.parseInt(request.get(GameRequestKey.AMOUNT.getKey()));
        int currentPriceOfResource = game.getCurrentPriceOfResource(player.getRole().getName());
        if (numberOfResource * currentPriceOfResource < player.getMoney()){
            player.addResouces(numberOfResource);
            player.substractMoney(numberOfResource * currentPriceOfResource);
            this.sendResponseToUser(player);
            return;
        }else{
            this.messenger.sendError("NOT ENOUGH MONEY");
            return;
        }
    }

    private void sendResponseToUser(Player player) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "OK");
        response.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        response.addProperty(GameResponseKey.MONEY.key, player.getMoney());
        messenger.sendSpecificMessageToAUser(response.toString());
        return;

    }


    private void checkArgumentOfRequest() throws MissArgumentToRequest{
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequest(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequest(GameRequestKey.USERID);
        if(!request.containsKey(GameRequestKey.AMOUNT.getKey()))
            throw new MissArgumentToRequest(GameRequestKey.AMOUNT);

        return;

    }
}
