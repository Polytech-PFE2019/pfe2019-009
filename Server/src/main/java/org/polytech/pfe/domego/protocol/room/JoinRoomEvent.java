package org.polytech.pfe.domego.protocol.room;

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
            this.checkParams();
        }catch (MissArgumentToRequestException e){
            this.messenger.sendErrorCuzMissingArgument(e.getMissKey().getKey());
            return;
        }

        Optional<Room> optionalRoom = new RoomAccessor().getRoomById(request.get(RoomRequestKey.ROOMID.getKey()));
        if(optionalRoom.isEmpty()){
            this.messenger.sendError("Room Not Found");
        }
        Room room = optionalRoom.get();
        boolean accepted = room.addPlayer(new Player(user,request.get(RoomRequestKey.USERNAME.getKey())));
        if(!accepted){
            this.messenger.sendError("Room full");
            return;
        }
        new UpdateRoomEvent(room).processEvent();

    }

    private void checkParams() throws MissArgumentToRequestException {
        if(!request.containsKey(RoomRequestKey.USERNAME.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.USERNAME);
        if(!request.containsKey(RoomRequestKey.ROOMID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.ROOMID);
    }


}
