package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;


public class JoinGameEvent implements EventProtocol {

    private Logger logger = Logger.getGlobal();
    private Messenger messenger;
    private Map<String, String> request;
    private GameAccessor gameAccessor;
    private WebSocketSession newSession;

    public JoinGameEvent(WebSocketSession session,Map request) {
        this.newSession = session;
        this.request = request;
        this.messenger = new Messenger(session);
        this.gameAccessor = new GameAccessor();
    }

    @Override
    public void processEvent() {
        try {
            this.checkParams();
        } catch (MissArgumentToRequestException e) {
            this.messenger.sendErrorCuzMissingArgument(e.getMissKey().getKey());
            return;
        }

        Optional<Game> gameOptional = this.gameAccessor.getGameById(request.get(GameRequestKey.GAMEID.getKey()));
        if(gameOptional.isEmpty()){
            logger.info("Game Not Found");
            this.messenger.sendError("Game not Found");
            return;
        }

        Game game = gameOptional.get();

        Optional<Player> optionalPlayer = game.getPlayerById(request.get(RoomRequestKey.USERID.getKey()));
        if(optionalPlayer.isEmpty()){
            logger.info("Player Not Found");
            this.messenger.sendError("Player not Found");
            return;
        }

        Player player = optionalPlayer.get();
        player.setSession(newSession);
        logger.info("JoinGameEvent : Le joueur : " + player.getName() + " a rejoint la partie : " + game.getId());
        List<Player> connectedPlayer = game.getPlayersPresent();
        int numberOfPlayerConnected = connectedPlayer.size();
        connectedPlayer.stream().forEach(player1 -> new Messenger(player1.getSession()).sendSpecificMessageToAUser(createResponseEvent(numberOfPlayerConnected,player1.getID(), game.getId()).toString()));

        if (numberOfPlayerConnected == game.getPlayers().size())
            new LaunchGameEvent(game).processEvent();

    }

    private JsonObject createResponseEvent(int numberOfPlayerConnected, String playerId, String gameID){
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,"OK");
        response.addProperty(GameResponseKey.USER_ID.key, playerId);
        response.addProperty(GameResponseKey.GAME_ID.key,gameID);
        response.addProperty(GameResponseKey.NOPC.key, numberOfPlayerConnected);
        return response;
    }

    private void checkParams() throws MissArgumentToRequestException {
        if(!request.containsKey(GameRequestKey.GAMEID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.GAMEID);
        if(!request.containsKey(GameRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(GameRequestKey.USERID);
    }

}
