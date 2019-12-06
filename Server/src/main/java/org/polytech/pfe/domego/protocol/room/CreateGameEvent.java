package org.polytech.pfe.domego.protocol.room;

import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.database.accessor.RoomAccessor;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateGameEvent implements EventProtocol {

    private final static Logger LOGGER = Logger.getGlobal();
    private Map<String,String> request;
    private Messenger messenger;
    private WebSocketSession user;

    public CreateGameEvent(WebSocketSession session, Map request) {
        this.user = session;
        this.messenger = new Messenger(session);
        this.request = request;
    }

    @Override
    public void processEvent() {
        if(!request.containsKey(RoomRequestKey.USERNAME.getKey())) {
            this.messenger.sendErrorCuzMissingArgument(RoomRequestKey.USERNAME.getKey());
            return;
        }
        String username = request.get(RoomRequestKey.USERNAME.getKey());
        Room newRoom = new Room(username + "'s lobby");

        Player player = new Player(user,username);
        newRoom.addPlayer(player);
        if(new RoomAccessor().addRoom(newRoom)){
            new UpdateRoomEvent(newRoom).processEvent();
            LOGGER.log(Level.INFO,
                    "CreateGameEvent : {0} has created a Room named : {1} with ID : {2}",
                    new Object[]{username, newRoom.getRoomName(), newRoom.getID()});

        }
        else{
            LOGGER.warning("CreateGameEvent : Error creating room");
            this.messenger.sendError("Error creating game ! Retry please !");

        }

    }
}
