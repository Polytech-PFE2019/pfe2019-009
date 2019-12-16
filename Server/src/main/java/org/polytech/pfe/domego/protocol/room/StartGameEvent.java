package org.polytech.pfe.domego.protocol.room;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.database.accessor.RoomAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.generator.GameType;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.polytech.pfe.domego.protocol.room.key.RoomResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
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
            this.messenger.sendError("Room not Found");
            return;
        }

        Room room = optionalRoom.get();

        Optional<Player> optionalPlayer = room.getPlayerById(request.get(RoomRequestKey.USERID.getKey()));
        if(optionalPlayer.isEmpty()){
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
        GameType gameType;
        try{
            gameType = GameType.valueOf(request.get(RoomRequestKey.GAME_TYPE.getKey()));
        }catch (IllegalArgumentException e){
            this.messenger.sendError("This type of game doesn't exist");
            return;
        }
        int timeOfProject = 0;
        if(request.containsKey(RoomRequestKey.DAYS.getKey())){
            timeOfProject = Integer.valueOf(request.get(RoomRequestKey.DAYS.getKey()));
        }

        int costOfProject = 0;
        if(request.containsKey(RoomRequestKey.COST.getKey())){
            costOfProject = Integer.valueOf(request.get(RoomRequestKey.COST.getKey()));
        }

        Game game = gameAccessor.createNewGameFromRoom(room, gameType,timeOfProject,costOfProject);
        String response = createStartGameResponse(game).toString();

        room.getPlayerList().stream().forEach(currentPlayer -> new Messenger(currentPlayer.getSession()).sendSpecificMessageToAUser(response));

        logger.log(Level.INFO,
                "StartGameEvent: Player name : {0} start the game for room with ID {1}",
                new Object[]{player.getName(), room.getID()});

        roomAccessor.removeRoom(room);

        logger.log(Level.INFO,"StartGameEvent : Delete room : {0}",  room.getID());
    }

    private JsonObject createStartGameResponse(Game game){
        JsonObject response = new JsonObject();
        response.addProperty(RoomResponseKey.RESPONSE.key,"START_GAME");
        response.addProperty(RoomResponseKey.GAMEID.key, game.getId());
        return response;
    }

    private void checkParams() throws MissArgumentToRequestException {
        if(!request.containsKey(RoomRequestKey.ROOMID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.ROOMID);
        if (!request.containsKey(RoomRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.USERID);
        if (!request.containsKey(RoomRequestKey.GAME_TYPE.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.GAME_TYPE);
        if (request.containsKey(RoomRequestKey.GAME_TYPE.getKey()) && GameType.valueOf(request.get(RoomRequestKey.GAME_TYPE.getKey())).equals(GameType.INTERMEDIATE)){
            if (!request.containsKey(RoomRequestKey.DAYS.getKey()))
                throw new MissArgumentToRequestException(RoomRequestKey.DAYS);
            if (!request.containsKey(RoomRequestKey.COST.getKey()))
                throw new MissArgumentToRequestException(RoomRequestKey.COST);
        }

    }
}
