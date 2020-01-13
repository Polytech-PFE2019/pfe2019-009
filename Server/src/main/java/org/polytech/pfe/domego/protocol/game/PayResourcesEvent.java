package org.polytech.pfe.domego.protocol.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PayResourcesEvent implements EventProtocol {

    private Map<String, ?> request;
    private Messenger messenger;
    private final Logger logger = Logger.getGlobal();

    public PayResourcesEvent(WebSocketSession session, Map request) {
        this.messenger = new Messenger(session);
        this.request = request;

    }

    @Override
    public void processEvent() {
        try {
            this.checkArgumentOfRequest();
        }catch (MissArgumentToRequestException missArgumentToRequest){
            this.messenger.sendErrorCuzMissingArgument(missArgumentToRequest.getMissKey().getKey());
            return;
        }

        Optional<Game> optionalGame = new GameAccessor().getGameById(String.valueOf(request.get(GameRequestKey.GAMEID.getKey())));
        if(optionalGame.isEmpty()){
            this.messenger.sendError("GAME NOT FOUND");
            return;
        }

        Game game = optionalGame.get();


        Optional<Player> optionalPlayer = game.getPlayers().stream().filter(player -> player.getID().equals(String.valueOf(request.get(GameRequestKey.USERID.getKey())))).findAny();

        if (optionalPlayer.isEmpty()){
            this.messenger.sendError("USER NOT FOUND");
            return;
        }

        Player player = optionalPlayer.get();

        this.payResources(game,player);

    }


    private void payResources(Game game, Player player){
        Type founderListType = new TypeToken<ArrayList<Payment>>(){}.getType();
        List<Payment> payments = new Gson().fromJson(request.get("payments").toString(), founderListType);
        Activity currentActivity = game.getCurrentActivity();
        if (!currentActivity.payResources(player, payments)){
            int totalAmount = payments.stream().mapToInt(Payment::getAmount).sum();
            logger.log(Level.INFO,"PaymentResourcesEvent : In the game {0}, the player named {1} has not enough resources, he has {2} and he need {3} to pay", new Object[]{game.getId(), player.getName(),player.getResourcesAmount(), totalAmount});
            return;
        }
        game.getPlayerById(player.getID()).ifPresent(this::sendResponseToUser);//Send Message to the player that payment is ok



        logger.log(Level.INFO,"PaymentResourcesEvent : In the game {0}, the player named {1} has realize {2} payment for the activity : {3}", new Object[]{game.getId(), player.getName(),payments.size(), currentActivity.getId()});
        logger.log(Level.INFO, "PaymentResourcesEvent : List Payment :  {0}", payments);
        logger.log(Level.INFO,"PaymentResourcesEvent : In the game {0}, the player named {1} has now {2} resources", new Object[]{game.getId(), player.getName(),player.getResourcesAmount()});
        new UpdatePaymentGameEvent(game, player).processEvent();

        if (currentActivity.isActivityDone()) {
            // if the activity status is not done yet it means the risk cards hadn't been drawn
            if (!currentActivity.getActivityStatus().equals(ActivityStatus.DONE)) {
                currentActivity.doneActivity();
                new DrawCardEvent(game).processEvent();
            }
            // otherwise it means the payment is coming from risk cards and we have to finish the activity.
            else {
                currentActivity.finishActivity();
                new ChangeActivityEvent(game).processEvent();

            }

        }




    }

    private void sendResponseToUser(Player player) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "PAY_RESOURCES");
        response.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());

        response.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getId());

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


