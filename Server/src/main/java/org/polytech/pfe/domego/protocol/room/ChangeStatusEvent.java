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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeStatusEvent implements EventProtocol {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Messenger messenger;
    private Map<String,String> request;
    private RoomAccessor roomAccessor;


    public ChangeStatusEvent(WebSocketSession session, Map request) {
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


        logger.log(Level.INFO,
                "ChangeStatusEvent : In game : {0} the player name : {1} has change status, new status : {2}",
                new Object[]{room.getID(),player.getName(), (state ? "Ready" : "Not Ready")});
        this.messenger.sendSpecificMessageToAUser(generateResponse(player,state).toString());

        new UpdateRoomEvent(room).processEvent();

    }

    private JsonObject generateResponse(Player player, boolean ready){
        JsonObject response = new JsonObject();
        response.addProperty("response", "CHANGE_STATUS");
        response.addProperty("ready", ready);
        response.addProperty("userID",player.getID());
        return response;
    }


    private void checkParams() throws MissArgumentToRequestException {
        if(!request.containsKey(RoomRequestKey.ROOMID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.ROOMID);
        if (!request.containsKey(RoomRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.USERID);
    }
}
