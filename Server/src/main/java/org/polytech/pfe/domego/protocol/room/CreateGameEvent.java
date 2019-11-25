package org.polytech.pfe.domego.protocol.room;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.buisness.Messenger;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.components.buisness.Room;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.polytech.pfe.domego.protocol.room.key.RoomResponseKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class CreateGameEvent implements EventProtocol {

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
        RoomInstance.getInstance().addRoom(newRoom);

        new UpdateRoomEvent(newRoom).processEvent();
    }
}
