package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BuyResourceEvent implements EventProtocol {

    private Logger logger = Logger.getGlobal();
    private Map<String,String> request;
    private GameAccessor gameAccessor;
    private Messenger messenger;

    public BuyResourceEvent(WebSocketSession session, Map request) {
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
        Optional<Player> optionalPlayer = game.getPlayers().stream().filter(player -> player.getID().equals(request.get(GameRequestKey.USERID.getKey()))).findAny();

        if (optionalPlayer.isEmpty()){
            this.messenger.sendError("USER NOT FOUND");
            return;
        }

        Player player = optionalPlayer.get();
        this.buyResource(game,player);

    }


    private void buyResource(Game game, Player player){
        Activity activity = game.getCurrentActivity();

        int roleID = player.getRole().getId();
        int numberOfResource = Integer.parseInt(request.get(GameRequestKey.AMOUNT.getKey()));
        int currentPriceOfResource = activity.getExchangeRateForRoleID(roleID);

        int totalPrice = numberOfResource * currentPriceOfResource;
        if ( totalPrice > player.getMoney()) {
            this.messenger.sendError("USER HAS NOT ENOUGH MONEY");
            return;
        }
        activity.buyResources(player,numberOfResource);

        logger.log(Level.INFO,
                "BuyResourceEvent : In game  {0} the player named : {1} has buy {2} resources. He has now : {3} resources and : {4} money.",
                new Object[]{game.getId(), player.getName(), numberOfResource, player.getResourcesAmount(), player.getMoney()});
        this.sendResponseToUser(player, totalPrice, numberOfResource);
    }

    private void sendResponseToUser(Player player, int price, int buyingResources) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "BUY_RESOURCES");
        response.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        response.addProperty(GameResponseKey.BUYING_RESOURCES.key,buyingResources);
        response.addProperty(GameResponseKey.MONEY.key, player.getMoney());
        response.addProperty(GameResponseKey.PRICE.key, price);
        response.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getId());
        messenger.sendSpecificMessageToAUser(response.toString());

    }


    private void checkArgumentOfRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.USERID);
        if(!request.containsKey(GameRequestKey.AMOUNT.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.AMOUNT);

    }
}
