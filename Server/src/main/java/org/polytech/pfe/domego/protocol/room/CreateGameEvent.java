package org.polytech.pfe.domego.protocol.room;

import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Room;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

public class CreateGameEvent implements EventProtocol {

    private Map<String,String> request;
    private WebSocketSession user;

    public CreateGameEvent(WebSocketSession session, Map<String,String> request) {
        this.user = session;
        this.request = request;
    }

    @Override
    public void processEvent() {
        if(!request.containsKey(RoomRequestKey.USERNAME.key)) {
            sendMessageToUser(user);
            return;
        }
        String username = request.get(RoomRequestKey.USERNAME.key);
        Room newRoom = new Room(username + "'s lobby");

        Player player = new Player(user,username);
        newRoom.addPlayer(player);
        RoomInstance.getInstance().addRoom(newRoom);

        //session.sendMessage(new TextMessage(room.createResponseRequest(player.getSocketID())));
    }



    private void sendMessageToUser(WebSocketSession session){
        try {
            user.sendMessage(new TextMessage("{response : \"KO\", message : \"bad request\"}"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
