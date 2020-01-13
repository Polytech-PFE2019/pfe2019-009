package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.polytech.pfe.domego.services.sockets.game.GameEventKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class GroupChatMessageEvent implements EventProtocol {

    private final static Logger logger = Logger.getGlobal();
    private Messenger messenger;
    private Map<String, ?> request;
    private GameAccessor gameAccessor;

    public GroupChatMessageEvent(WebSocketSession session, Map request) {
        this.messenger = new Messenger(session);
        this.request = request;
        this.gameAccessor = new GameAccessor();
    }

    @Override
    public void processEvent() {
        try {
            this.checkParamsRequest();
        } catch (MissArgumentToRequestException e) {
            this.messenger.sendErrorCuzMissingArgument(e.getMissKey().getKey());
            return;
        }

        Optional<Game> optionalGame = gameAccessor.getGameById(String.valueOf(request.get(GameRequestKey.GAMEID.getKey())));
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


        Player playerMessage = optionalPlayer.get();

        game.getPlayers().stream().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJsonResponse(playerMessage).toString()));


    }

    private JsonElement createJsonResponse(Player player){
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, GameEventKey.MSG_GROUP_CHAT.key);
        response.addProperty(GameResponseKey.MESSAGE.key, request.get(GameRequestKey.MESSAGE.getKey()).toString());
        response.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getId());
        response.addProperty(GameResponseKey.USER_ID.key, player.getID());
        return response;
    }

    private void checkParamsRequest() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.USERID);
        if(!request.containsKey(GameRequestKey.MESSAGE.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.MESSAGE);
    }
}
