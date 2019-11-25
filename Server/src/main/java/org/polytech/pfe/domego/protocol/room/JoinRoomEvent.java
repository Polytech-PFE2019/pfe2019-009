package org.polytech.pfe.domego.protocol.room;

import org.polytech.pfe.domego.components.buisness.Messenger;
import org.polytech.pfe.domego.components.buisness.Room;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequest;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;

public class JoinRoomEvent implements EventProtocol {

    private Messenger messenger;
    private Map<String, String> request;
    private WebSocketSession user;

    public JoinRoomEvent(WebSocketSession session, Map request) {
        this.user = session;
        this.messenger = new Messenger(session);
        this.request = request;
    }

    @Override
    public void processEvent() {
        try {
            this.checkArguement();
        }catch (MissArgumentToRequest e){
            this.messenger.sendErrorCuzMissingArgument(e.getMissKey().getKey());
            return;
        }

        Optional<Room> optionalRoom = RoomInstance.getInstance().getRoomById(request.get(RoomRequestKey.ROOMID.getKey()));
        if(! optionalRoom.isPresent()){
            this.messenger.sendError("Room Not Found");
        }

        Room room = optionalRoom.get();
        if(!room.addPlayer(new Player(user,request.get(RoomRequestKey.USERNAME.getKey())))){
            this.messenger.sendError("Room full");
            return;
        }
        new UpdateRoomEvent(room).processEvent();

    }


    private void checkArguement() throws MissArgumentToRequest{
        if(!request.containsKey(RoomRequestKey.USERNAME.getKey()))
            throw new MissArgumentToRequest(RoomRequestKey.USERNAME);
        if(!request.containsKey(RoomRequestKey.ROOMID.getKey()))
            throw new MissArgumentToRequest(RoomRequestKey.ROOMID);
        return;
    }


}
