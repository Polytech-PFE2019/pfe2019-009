package org.polytech.pfe.domego.protocol.room;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomResponseKey;

public class UpdateRoomEvent implements EventProtocol {

    private Room currentRoom;

    UpdateRoomEvent(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public void processEvent() {
       currentRoom.getPlayerList().stream().forEach(
               player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createUpdateResponse(player.getID()))
       );
    }

    private String createUpdateResponse(String userID) {

        JsonObject response = new JsonObject();

        response.addProperty(RoomResponseKey.RESPONSE.key, RoomResponseKey.UPDATE.key);
        response.addProperty(RoomResponseKey.ROOMID.key, currentRoom.getID());
        response.addProperty(RoomResponseKey.USERID.key, userID);
        response.addProperty(RoomResponseKey.HOSTID.key, currentRoom.getHostID());

        JsonArray players = new JsonArray();
        for (Player player : currentRoom.getPlayerList()) {
            JsonObject rep = new JsonObject();
            rep.addProperty(RoomResponseKey.USERNAME.key, player.getName());
            rep.addProperty(RoomResponseKey.READY.key, currentRoom.playerIsReady(player));
            rep.addProperty(RoomResponseKey.ROLEID.key, player.getRole().getId());
            players.add(rep);
        }
        response.add(RoomResponseKey.PLAYERS.key, players);

        return response.toString();

    }
}
