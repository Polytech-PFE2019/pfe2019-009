package org.polytech.pfe.domego.protocol.room;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.database.accessor.RoomAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class ChangeStatusEvent implements EventProtocol {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Messenger messenger;
    private Map<String,String> request;
    private RoomAccessor roomAccessor;


    public ChangeStatusEvent(WebSocketSession session, Map<String, String> request) {
        this.messenger = new Messenger(session);
        this.request = request;
        this.roomAccessor = new RoomAccessor();
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
        boolean state = room.changeStateOfPlayer(player);

        JsonObject response = new JsonObject();
        response.addProperty("response", "CHANGE_STATUS");
        response.addProperty("ready", state);
        response.addProperty("userID",player.getID());
        logger.info("In game : " + room.getID() + " the player name : " + player.getName() + " has change status, new status : " + (state ? "Ready" : "Not Ready"));
        this.messenger.sendSpecificMessageToAUser(response.toString());

        new UpdateRoomEvent(room).processEvent();

    }


    private void checkParams() throws MissArgumentToRequestException {
        if(!request.containsKey(RoomRequestKey.ROOMID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.ROOMID);
        if (!request.containsKey(RoomRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.USERID);
    }
}
