package org.polytech.pfe.domego;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.Room;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class RoomRequestHandler {

    private final String RESPONSE_OK = " {\"response\":\"OK\"}";
    private final String RESPONSE_KO = " {\"response\":\"KO\"}";

    private RoomInstance roomInstance;
    private final RoleAccessor roleAccessor;

    @Autowired
    public RoomRequestHandler(RoleAccessor roleDB){
        roomInstance = RoomInstance.getInstance();
        this.roleAccessor = roleDB;
    }

    public void handleRequest(WebSocketSession session, Map<String, String> request) throws Exception {

        if(!request.containsKey("request")) {
            throw new Exception("bad request : must be of type {\"request\":\"REQUEST_NAME\'}");
        }
        String requestName = request.get("request");

        System.out.println("REQUETE : ");
        for (String key : request.keySet()) {
            System.out.println("KEY : " + key + " VALUE : " + request.get(key).toString());
        }

        switch(requestName){
            case "CREATE_GAME" :
                if(!request.containsKey("username")) {
                    session.sendMessage(new TextMessage(responseKO()));

                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',username:\"USERNAME\"}");
                }
                System.out.println("CREATE GAME");
                handleRoomCreation(session, request.get("username"));
                break;
            case "JOIN_GAME" :
                if(!request.containsKey("username") || !request.containsKey("roomID")) {
                    session.sendMessage(new TextMessage(responseKO()));

                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',username:\"USERNAME\",roomID:ROOMID}");
                }
                System.out.println(" JOIN GAME");
                handleJoiningRoom(session, Integer.parseInt(request.get("roomID")),request.get("username"));
                break;

            case "CHOOSE_ROLE" :
                if(!request.containsKey("roomID") || !request.containsKey("userID") || !request.containsKey("roleID")){
                    session.sendMessage(new TextMessage(responseKO()));
                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',userID:\"userID\",roomID:ROOMID,roleID:ROLEID}");
                }
                handleChoosingRole(session, Integer.parseInt(request.get("roomID")),request.get("userID"),Integer.parseInt(request.get("roleID")));
                break;
            case "CHANGE_STATUS" :
                if(request.get("roomID")==null || request.get("userID")==null) {
                    session.sendMessage(new TextMessage(responseKO()));
                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',userID:\"userID\",roomID:ROOMID}");
                }
                handleChangingStatus(session, Integer.parseInt(request.get("roomID")),request.get("userID"));
                break;
            case "START_GAME" :
                if(request.get("roomID")==null || request.get("userID")==null) {
                    session.sendMessage(new TextMessage(responseKO()));
                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',userID:\"userID\",roomID:ROOMID,roleID:ROLEID}");
                }
                handleStartingGame(session, Integer.parseInt(request.get("roomID")),request.get("userID"));
                break;
            case "LEAVE_ROOM" :
                if(request.get("roomID")==null || request.get("userID")==null) {
                    session.sendMessage(new TextMessage(responseKO()));
                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',userID:\"userID\",roomID:ROOMID,roleID:ROLEID}");
                }
                System.out.println("START GAME");
                handleLeavingRoom(session, Integer.parseInt(request.get("roomID")),request.get("userID"));
                break;
            default:
                break;
        }
    }

    private void handleRoomCreation(WebSocketSession session, String username) throws IOException {
        System.out.println(username);
        Room room = new Room("La Room",this.roomInstance.getRoomList().size());

        Player player = new Player(session,username);
        room.addPlayer(player);
        this.roomInstance.addRoom(room);
        System.out.println(this.roomInstance.numberOfRooms()+" rooms");

        session.sendMessage(new TextMessage(room.createResponseRequest(player.getSocketID())));


    }

    private void handleJoiningRoom(WebSocketSession session,  int roomID, String username) throws IOException {
        Room room = this.roomInstance.getRoomByID(roomID);

        Player player = new Player(session,username);
        room.addPlayer(player);
        System.out.println(this.roomInstance.getRoomByID(roomID).getPlayerList().size()+" players in room of roomID"+roomID);

        System.out.println(room.createResponseRequest(player.getSocketID()));
        session.sendMessage(new TextMessage(room.createResponseRequest(player.getSocketID())));

        updateRoomForAllPlayers(room,player);
    }

    //TODO Corriger cette partie
    private void handleChoosingRole(WebSocketSession session,  int roomID, String playerID, int roleID) throws IOException {
        Room room = this.roomInstance.getRoomByID(roomID);

        Player player = room.getPlayerByID(playerID);

        RoleType roleType = RoleType.getRoleType(roleID);

        Optional<Role> role = roleAccessor.getSpecificRoleById(roleID);

        if (!role.isPresent()){
            System.out.println("ERROR this role doesn't existe");
            return;
        }


        player.setRole(role.get());


        Player updatedPlayer = this.roomInstance.getRoomByID(roomID).getPlayerByID(playerID);
        System.out.println(updatedPlayer.getName()+" has now the role : "+updatedPlayer.getRole().getName());

        JsonObject response = new JsonObject();
        response.addProperty("response", "CHOOSING_ROLE");
        response.addProperty("roomID", roomID);
        response.addProperty("userID", playerID);
        response.addProperty("roleID", roleID);
        session.sendMessage(new TextMessage(response.toString()));

        updateRoomForAllPlayers(room,player);


    }

    private void updateRoomForAllPlayers(Room room, Player playerAlreadyUpdated) throws IOException {

        for(Player player : room.getPlayerList()){
            if(!playerAlreadyUpdated.equals(player)) {
                player.getSession().sendMessage(new TextMessage(room.createUpdateResponse()));
            }
        }
    }

    private void handleChangingStatus(WebSocketSession session,  int roomID, String playerID) throws IOException {
        Room room = this.roomInstance.getRoomByID(roomID);
        Player player = room.getPlayerByID(playerID);
        player.changeReady();

        JsonObject response = new JsonObject();
        response.addProperty("response", "CHOOSING_ROLE");
        response.addProperty("ready", player.isReady());
        response.addProperty("userID", playerID);
        session.sendMessage(new TextMessage(response.toString()));

        updateRoomForAllPlayers(room,player);


    }

    private void handleStartingGame(WebSocketSession session,  int roomID, String playerID) throws IOException {
        Room room = this.roomInstance.getRoomByID(roomID);
        room.createGame(room.getPlayerList());


        JsonObject response = new JsonObject();
        response.addProperty("response", "START_GAME");
        for(Player player : room.getPlayerList()){
                player.getSession().sendMessage(new TextMessage(response.toString()));
        }
    }

    private void handleLeavingRoom(WebSocketSession session,  int roomID, String playerID) throws IOException {
        Room room = this.roomInstance.getRoomByID(roomID);
        room.createGame(room.getPlayerList());
        Player player = room.getPlayerByID(playerID);
        room.removePlayer(player);

        JsonObject response = new JsonObject();
        response.addProperty("response", "LEAVE_ROOM");
        response.addProperty("userID", playerID);
        session.sendMessage(new TextMessage(response.toString()));
        updateRoomForAllPlayers(room,player);

    }


        private String responseOK(){
        JsonObject response = new JsonObject();
        response.addProperty("response", "OK");
        return response.toString();
    }

    private String responseKO(){
        JsonObject response = new JsonObject();
        response.addProperty("response", "KO");
        return response.toString();
    }
}

