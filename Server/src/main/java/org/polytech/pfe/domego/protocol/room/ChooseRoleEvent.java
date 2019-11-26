package org.polytech.pfe.domego.protocol.room;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.polytech.pfe.domego.database.accessor.RoomAccessor;
import org.polytech.pfe.domego.exceptions.MissArgumentToRequestException;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.room.key.RoomRequestKey;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;


public class ChooseRoleEvent implements EventProtocol {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Messenger messenger;
    private Map<String, String> request;
    private RoomAccessor roomAccessor;
    private RoleAccessor roleAccessor;

    public ChooseRoleEvent(WebSocketSession session, Map<String, String> request, RoleAccessor roleAccessor) {
        this.messenger = new Messenger(session);
        this.request = request;
        this.roomAccessor = new RoomAccessor();
        this.roleAccessor = roleAccessor;
    }

    @Override
    public void processEvent() {

        try {
            this.checkArgument();
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

        Optional<Role> optionalRole = roleAccessor.getSpecificRoleById(Integer.parseInt(request.get(RoomRequestKey.ROLEID.getKey())));

        if (optionalRole.isEmpty()){
            this.messenger.sendError("Role Not Found");
            return;
        }

        Role role = optionalRole.get();

        if(room.getPlayerList().stream().anyMatch(player1 -> player1.getRole().equals(role)) && !role.getName().equals(RoleType.NON_DEFINI)){
            this.messenger.sendError("Role is already taken !");
            return;
        }


        player.setRole(role);


        System.out.println(player.getName()+" has now the role : "+player.getRole().getName());

        JsonObject response = new JsonObject();
        response.addProperty("response", "CHOOSE_ROLE");
        response.addProperty("roomID", room.getID());
        response.addProperty("userID", player.getID());
        response.addProperty("roleID", role.getId());
        this.messenger.sendSpecificMessageToAUser(response.toString());
        logger.info("In game : " + room.getID() + " the player name : " + player.getName() + " has change role, new role : " + player.getRole().getName().getName());

        new UpdateRoomEvent(room).processEvent();

    }

    private void checkArgument() throws MissArgumentToRequestException {
        if(!request.containsKey(RoomRequestKey.ROOMID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.ROOMID);
        if(!request.containsKey(RoomRequestKey.USERID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.USERID);
        if(!request.containsKey(RoomRequestKey.ROLEID.getKey()))
            throw new MissArgumentToRequestException(RoomRequestKey.USERID);

    }
}
