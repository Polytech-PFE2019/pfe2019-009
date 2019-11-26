package org.polytech.pfe.domego.protocol.room;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.database.accessor.RoomAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.polytech.pfe.domego.protocol.room.key.RoomResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class StartGameEvent implements EventProtocol {
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Messenger messenger;
    private Map<String,String> request;
    private RoomAccessor roomAccessor;
    private GameAccessor gameAccessor;

    public StartGameEvent(WebSocketSession session, Map request) {
        this.messenger = new Messenger(session);
        this.request = request;
        this.roomAccessor = new RoomAccessor();
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

        Optional<Room> optionalRoom = this.roomAccessor.getRoomById(request.get(RoomRequestKey.ROOMID.getKey()));
        if(optionalRoom.isEmpty()){
            logger.info("Room Not Found");
            this.messenger.sendError("Room not Found");
            return;
        }

        Room room = optionalRoom.get();

        Optional<Player> optionalPlayer = room.getPlayerById(request.get(RoomRequestKey.USERID.getKey()));
        if(optionalPlayer.isEmpty()){
            logger.info("Player Not Found");
            this.messenger.sendError("Player not Found");
            return;
        }
        Player player = optionalPlayer.get();

        if(!player.getID().equals(room.getHostID())){
            this.messenger.sendError("You aren't the HOST player");
            return;
        }
        if(!room.isFull()){
            this.messenger.sendError("You must be 6 to start");
            return;
        }
        if (!room.everybodyGotARole()){
            this.messenger.sendError("At least one player has no role");
            return;
        }
        if(!room.everybodyIsReady()){
            this.messenger.sendError("Everybody is not ready");
            return;
        }

        Game game = gameAccessor.createNewGameFromRoom(room);
        String response = createStartGameResponse(game).toString();

        room.getPlayerList().forEach(currentPlayer -> new Messenger(currentPlayer.getSession()).sendSpecificMessageToAUser(response));

        logger.info("Player name : " + player.getName() + " start the game for room with ID" + room.getID());
    }

    private JsonObject createStartGameResponse(Game game){
        JsonObject response = new JsonObject();
        response.addProperty(RoomResponseKey.RESPONSE.key,"OK");
        response.addProperty(RoomResponseKey.GAMEID.key, game.getId());
        return response;
    }

    private void checkParams() throws MissArgumentToRequestException {
        if(!request.containsKey(RoomRequestKey.ROOMID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.ROOMID);
        if (!request.containsKey(RoomRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.USERID);
    }
}